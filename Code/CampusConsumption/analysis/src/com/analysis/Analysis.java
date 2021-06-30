package com.analysis;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.results.id_typeOfConsumption;
import com.results.numbersOfCanteen;
import com.utils.Category;
import com.utils.JDBCUtils;
import com.utils.KnnValueBean;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.print.DocFlavor;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Analysis {


    public static void main(String[] args) throws Exception {
        // 查询本月各食堂就餐人次
        List<numbersOfCanteen> list = Analysis.getNumbersOfCanteen();
        list.forEach(System.out::println);

        int k = 6;      // 设置聚类类别数目

        // k-means 聚类
        List<List<id_typeOfConsumption>> clusterList = new ArrayList<>();
        List<id_typeOfConsumption> clusteringCenterT = new ArrayList<>();
        KMeans2 kMeans = Analysis.getKMeans();
        kMeans.setK(k);
        kMeans.setMaxClusterTimes(2000);
        clusterList = kMeans.clustering();  // 聚类的结果
        clusteringCenterT = kMeans.getClusteringCenterT();  // 质心
//         将定向聚类的结果写入mysql
        createResultsOfKMeans(clusterList, clusteringCenterT, "data_kmeans");
//
//        // knn分类
//        // 将低消费人群通过聚类结果进行分类
//        // 获取聚类结果作为样本数据集
////        KNN2 knn = getKMeansDataSorce(clusterList);
//
//        KNN2 knn = getKMeansDataSorce("data1_3_2");
//        knn.setK(10);
////         获取低消费人群数据
//        List<id_typeOfConsumption> lowCostData = getLowCost();
////         进行分类，获取分类后的集合
//        List<KnnValueBean<id_typeOfConsumption>> results = knn.getResults(lowCostData);
////         将分类的结果写入mysql
//        createResultsOfKNN(results, "data1_3_4");


    }

    // 查询本月各食堂就餐人次
    public static List<numbersOfCanteen> getNumbersOfCanteen() {
        Connection connection = null;
        List<numbersOfCanteen> list = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT `消费地点` canteen, COUNT(*) number FROM data1_2_1 WHERE `消费类型` = '消费' GROUP BY `消费地点` ORDER BY number DESC;";
            BeanListHandler<numbersOfCanteen> handler = new BeanListHandler<>(numbersOfCanteen.class);
            list = runner.query(connection, sql, handler);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return list;
    }

    // 定向k-means聚类
    public static KMeans2 getKMeans() {
        Connection connection = null;
        KMeans2 kMeans;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
//            String sql = "SELECT 序号 id,ROUND(SUM(IF(消费金额类型 = 1,消费金额,0)),2) costAcademic,ROUND(SUM(IF(消费金额类型 = 2 OR 消费金额类型 = 3,消费金额,0)),2) costEating FROM view1_2_1 GROUP BY 序号;";
            String sql = "SELECT * FROM view1_4_1;";
            BeanListHandler<id_typeOfConsumption> handler = new BeanListHandler<>(id_typeOfConsumption.class);

            List<id_typeOfConsumption> dataArray = runner.query(connection, sql, handler);
            int maxClusterTimes = 500;  //最大迭代次数

            kMeans = new KMeans2(dataArray, 3, maxClusterTimes);
            return kMeans;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return null;
    }

    // 将定向聚类的结果写入mysql
    public static void createResultsOfKMeans(List<List<id_typeOfConsumption>> clusterList, List<id_typeOfConsumption> clusteringCenterT, String table) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            // 清空表
            String sql = "truncate " + table + ";";
            runner.update(connection, sql);
            sql = "insert into " + table + " values(?,?,?,?);";
            // 最前面插入聚类中心
            for (int i = 0; i < clusteringCenterT.size(); i++) {
                runner.update(connection, sql, -(i + 1), clusteringCenterT.get(i).getCostAcademic(), clusteringCenterT.get(i).getCostEating(), i + 1);
            }
            // 插入聚类完成的数据
            for (int i = 0; i < clusterList.size(); i++) {
                List<id_typeOfConsumption> list = clusterList.get(i);
                for (id_typeOfConsumption obj : list) {
                    runner.update(connection, sql, obj.getId(), obj.getCostAcademic(), obj.getCostEating(), i + 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    // 获取聚类结果作为样本数据集
    public static KNN2 getKMeansDataSorce(List<List<id_typeOfConsumption>> clusterList) {
        KNN2 knn = new KNN2();
        for (int i = 0; i < clusterList.size(); i++) {
            List<id_typeOfConsumption> list = clusterList.get(i);
            for (id_typeOfConsumption data : list) {
                knn.addRecord(data, i + 1 + "");
            }
        }
        return knn;
    }

    // 获取待分类集：低消费人群数据
    public static List<id_typeOfConsumption> getLowCost() {
        Connection connection = null;
        List<id_typeOfConsumption> list = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "SELECT 序号 id,ROUND(SUM(IF(消费金额类型 = 1,消费金额,0)),2) costAcademic,ROUND(SUM(IF(消费金额类型 = 2 OR 消费金额类型 = 3,消费金额,0)),2) costEating FROM view1_2_3 GROUP BY 序号;";
            BeanListHandler<id_typeOfConsumption> handler = new BeanListHandler<>(id_typeOfConsumption.class);
            list = runner.query(connection, sql, handler);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return list;
    }

    // 将分类的结果写入mysql
    public static void createResultsOfKNN(List<KnnValueBean<id_typeOfConsumption>> results, String table) {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            // 清空表
            String sql = "truncate " + table + ";";
            runner.update(connection, sql);
            sql = "insert into " + table + " values(?,?,?,?);";
            for (KnnValueBean<id_typeOfConsumption> bean : results) {
                id_typeOfConsumption value = bean.getValue();
                runner.update(connection, sql, value.getId(), value.getCostAcademic(), value.getCostEating(), bean.getTypeId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    // 读取聚类结果
    public static KNN2 getKMeansDataSorce(String table) {
        KNN2 knn = null;
        Connection connection = null;
        try {
            knn = new KNN2();
            connection = JDBCUtils.getConnection();
            QueryRunner runner = new QueryRunner();
            String sql = "select id,costAcademic,costEating from " + table + " where id>=0;";
            BeanListHandler<id_typeOfConsumption> handler = new BeanListHandler<>(id_typeOfConsumption.class);
            List<id_typeOfConsumption> listValue = runner.query(connection, sql, handler);
            String sql2 = "select category type from " + table + " where id>=0;";
            BeanListHandler<Category> handler2 = new BeanListHandler<>(Category.class);
            List<Category> listCategory = runner.query(connection, sql2, handler2);

            for (int i = 0; i < listValue.size(); i++) {
                knn.addRecord(listValue.get(i), listCategory.get(i).getType() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return knn;
    }
}
