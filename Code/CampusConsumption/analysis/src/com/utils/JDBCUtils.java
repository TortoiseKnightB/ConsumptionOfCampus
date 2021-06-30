package com.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


public class JDBCUtils {
    private static DataSource dataSource;

    // 生成数据库连接池
    static {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从数据库连接池中获取一个连接
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    // 关闭资源连接
    public static void closeResource(Connection connection, Statement statement, ResultSet resultSet) {
        DbUtils.closeQuietly(connection);
        DbUtils.closeQuietly(statement);
        DbUtils.closeQuietly(resultSet);
    }
}
