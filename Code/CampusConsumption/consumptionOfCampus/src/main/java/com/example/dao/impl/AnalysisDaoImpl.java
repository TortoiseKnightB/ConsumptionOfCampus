package com.example.dao.impl;

import com.example.clustering.KMeans2;
import com.example.clustering.id_typeOfConsumption;
import com.example.dao.AnalysisDao;
import com.example.pojo.*;
import com.example.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author T-Knight
 * @create 2021-05-23 6:06 下午
 */
// 查询数据库，Dao层级操作
public class AnalysisDaoImpl extends BaseDao implements AnalysisDao {
    @Override
    public List<CanteenPeriod> queryPeopleInCanteens(int count) {
        String sql = "SELECT `消费地点` name, COUNT(*) count FROM data1_2_1 WHERE `消费类型` = '消费' GROUP BY `消费地点` ORDER BY count DESC;";
        List<CanteenPeriod> res = queryForList(CanteenPeriod.class, sql);
        // 将靠后地点合并为其他
        if (res.size() > count) {
            CanteenPeriod other = new CanteenPeriod("其他", 0);
            while (res.size() > count - 1) {
                other.setCount(other.getCount() + res.get(count - 1).getCount());
                res.remove(count - 1);
            }
            res.add(other);
        }
        return res;
    }

    @Override
    public List<CanteenPeriod> queryPeopleInCanteensByPeriod(int count, String period) {
        String sql = "SELECT 消费地点 name, SUM(IF(`消费时间段` = ?, 1, 0)) count FROM view1_2_1 GROUP BY `消费地点` ORDER BY count DESC;";
        List<CanteenPeriod> res = queryForList(CanteenPeriod.class, sql, period);
        // 将靠后地点合并为其他
        if (res.size() > count) {
            CanteenPeriod other = new CanteenPeriod("其他", 0);
            while (res.size() > count - 1) {
                other.setCount(other.getCount() + res.get(count - 1).getCount());
                res.remove(count - 1);
            }
            res.add(other);
        }
        return res;
    }

    @Override
    public List<CostInApril> queryCostInAprilAll() {
        String sql = "SELECT 消费日期 date, ROUND(SUM(IF(消费时间点 BETWEEN 6 AND 9, 消费金额, 0)), 2) costMorning, ROUND(SUM(IF(消费时间点 BETWEEN 10 AND 13, 消费金额, 0)), 2) costNoon, ROUND(SUM(IF(消费时间点 BETWEEN 14 AND 19, 消费金额, 0)), 2) costEvening, ROUND(SUM(IF(消费时间点 BETWEEN 0 AND 5 OR 消费时间点 BETWEEN 20 AND 23, 消费金额, 0)), 2) costNight FROM view1_2_1 GROUP BY 消费日期 ORDER BY 消费日期;";
        List<CostInApril> res = queryForList(CostInApril.class, sql);
        return res;
    }

    @Override
    public List<CanteenPeriod> queryPeopleInCanteensByWorkOrNonWork(int count, boolean isWorkDay) {
        String sql;
        if (isWorkDay) {
            // 查询工作日情况
            sql = "SELECT 消费地点 name, COUNT(*) count FROM view1_2_1 WHERE 消费星期 BETWEEN 1 AND 5 GROUP BY 消费地点 ORDER BY count DESC;";
        } else {
            // 查询非工作日情况
            sql = "SELECT 消费地点 name, COUNT(*) count FROM view1_2_1 WHERE 消费星期 BETWEEN 6 AND 7 GROUP BY 消费地点 ORDER BY count DESC;";
        }
        List<CanteenPeriod> res = queryForList(CanteenPeriod.class, sql);
        // 将靠后地点合并为其他
        if (res.size() > count) {
            CanteenPeriod other = new CanteenPeriod("其他", 0);
            while (res.size() > count - 1) {
                other.setCount(other.getCount() + res.get(count - 1).getCount());
                res.remove(count - 1);
            }
            res.add(other);
        }
        return res;
    }

    @Override
    public List<PeopleTime> queryPeopleTime(boolean isWork) {
        String sql;
        if (isWork) {
            sql = "SELECT 消费时间点 costTime, COUNT(*) count FROM view1_2_1 WHERE 消费星期 BETWEEN 1 AND 5 GROUP BY 消费时间点 ORDER BY costTime;";
        } else {
            sql = "SELECT 消费时间点 costTime, COUNT(*) count FROM view1_2_1 WHERE 消费星期 BETWEEN 6 AND 7 GROUP BY 消费时间点 ORDER BY costTime;";
        }
        List<PeopleTime> resQuery = queryForList(PeopleTime.class, sql);
        // 查询结果可能存在某些时间点没有数据，将没有数据的时间点的值设置为0
        List<PeopleTime> res = new ArrayList<>();
        // 初始化为全0
        for (int i = 0; i <= 23; i++) {
            res.add(new PeopleTime(i, 0));
        }
        // 将查询结果覆盖，没有覆盖的部分保持为0
        for (PeopleTime p : resQuery) {
            res.set(p.getCostTime(), p);
        }
        return res;
    }

    @Override
    public List<Major> queryPeopleInMajor() {
        String sql = "SELECT v1.专业名称 name, ROUND(COUNT(*) / SUM(DISTINCT v2.专业总人数)) timesPer, ROUND(SUM(消费金额) / SUM(DISTINCT v2.专业总人数), 2) costPer, ROUND(COUNT(DISTINCT IF(消费时间段 = '早餐', 序号, NULL)) / SUM(DISTINCT v2.专业总人数), 4) morningPenetration, ROUND(COUNT(DISTINCT IF(消费时间段 = '中餐', 序号, NULL)) / SUM(DISTINCT v2.专业总人数), 4) noonPenetration, ROUND(COUNT(DISTINCT IF(消费时间段 = '晚餐', 序号, NULL)) / SUM(DISTINCT v2.专业总人数), 4) eveningPenetration, ROUND(COUNT(DISTINCT IF(消费时间段 = '夜宵', 序号, NULL)) / SUM(DISTINCT v2.专业总人数), 4) nightPenetration FROM view1_2_1 v1 LEFT JOIN view1_2_2 v2 ON v1.专业名称 = v2.专业名称 GROUP BY name;";
        List<Major> majors = queryForList(Major.class, sql);
        return majors;
    }

    @Override
    public List<CanteenPeriod> queryPeopleInLowCostCanteens(int count) {
        String sql = "SELECT 消费地点 name, COUNT(*) count FROM view1_2_3 GROUP BY 消费地点 ORDER BY count DESC;";
        List<CanteenPeriod> res = queryForList(CanteenPeriod.class, sql);
        // 将靠后地点合并为其他
        if (res.size() > count) {
            CanteenPeriod other = new CanteenPeriod("其他", 0);
            while (res.size() > count - 1) {
                other.setCount(other.getCount() + res.get(count - 1).getCount());
                res.remove(count - 1);
            }
            res.add(other);
        }
        return res;
    }

    @Override
    public List<PeopleTime> queryPeopleInLowCostTime(boolean isWork) {
        String sql;
        if (isWork) {
            sql = "SELECT 消费时间点 costTime, COUNT(*) count FROM view1_2_3 WHERE 是否工作日 = 1 GROUP BY 消费时间点 ORDER BY costTime;";
        } else {
            sql = "SELECT 消费时间点 costTime, COUNT(*) count FROM view1_2_3 WHERE 是否工作日 = 0 GROUP BY 消费时间点 ORDER BY costTime;";
        }
        List<PeopleTime> resQuery = queryForList(PeopleTime.class, sql);
        // 查询结果可能存在某些时间点没有数据，将没有数据的时间点的值设置为0
        List<PeopleTime> res = new ArrayList<>();
        // 初始化为全0
        for (int i = 0; i <= 23; i++) {
            res.add(new PeopleTime(i, 0));
        }
        // 将查询结果覆盖，没有覆盖的部分保持为0
        for (PeopleTime p : resQuery) {
            res.set(p.getCostTime(), p);
        }
        return res;
    }

    @Override
    public List<ClusteringBean> queryPeopleInClusteringKmeans() {
        // 查询类别数k
        String sql = "SELECT COUNT(*) FROM data_kmeans WHERE id < 0;";
        Object obj = queryForSingleValue(sql);
        // obj为Long类型，先将Long转换为字符串，再转换为int
        int k = Integer.parseInt(String.valueOf(obj));

        // 查询聚类结果
        List<ClusteringBean> clusteringBeans = new ArrayList<>();
        sql = "SELECT costAcademic x, costEating y FROM data_kmeans WHERE id > 0 AND category = ?;";
        for (int i = 1; i <= k; i++) {
            List<Data> data = queryForList(Data.class, sql, i);
            ClusteringBean clusteringBean = new ClusteringBean("类别" + i, data);
            clusteringBeans.add(clusteringBean);
        }
        return clusteringBeans;
    }

    @Override
    public void updateClusteringKmeans(int k) {
        List<List<id_typeOfConsumption>> clusterList = new ArrayList<>();
        List<id_typeOfConsumption> clusteringCenterT = new ArrayList<>();
        KMeans2 kMeans = getKMeans();
        kMeans.setK(k);
        kMeans.setMaxClusterTimes(2000);
        clusterList = kMeans.clustering();  // 聚类的结果
        clusteringCenterT = kMeans.getClusteringCenterT();  // 质心
        // 将定向聚类的结果写入mysql
        createResultsOfKMeans(clusterList, clusteringCenterT, "data_kmeans");
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

    @Override
    public TimesCostPer queryTimesCostPer() {
        String sql = "SELECT ROUND(SUM(消费金额) / COUNT(DISTINCT 序号), 2) times, ROUND(COUNT(*) / COUNT(DISTINCT 序号)) cost FROM view1_2_1;";
        TimesCostPer res = queryForOne(TimesCostPer.class, sql);
        return res;
    }

    @Override
    public List<TimesCostPerCanteens> queryTimesCostPerOfCanteens() {
        String sql = "SELECT 消费地点 name, ROUND(SUM(消费金额) / COUNT(DISTINCT 序号), 2) cost, ROUND(COUNT(*) / COUNT(DISTINCT 序号)) times FROM view1_2_1 GROUP BY 消费地点;";
        List<TimesCostPerCanteens> res = queryForList(TimesCostPerCanteens.class, sql);
        return res;
    }
}
