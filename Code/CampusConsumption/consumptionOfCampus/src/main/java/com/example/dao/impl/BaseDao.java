package com.example.dao.impl;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.example.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author T-Knight
 * @create 2021-05-23 6:06 下午
 */
// 进行一些数据库的操作
public class BaseDao {

    private QueryRunner runner = new QueryRunner();

    // 修改数据库操作
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return runner.update(connection, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return -1;
    }


    // 查询单条语句
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return runner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return null;
    }


    // 查询多条语句
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return runner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return null;
    }


    // 查询特殊单值
    public Object queryForSingleValue(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return runner.query(connection, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
        return null;
    }

}

