package com.test;

import com.example.utils.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author T-Knight
 * @create 2021-05-23 2:33 下午
 */
class JDBCUtilsTest {

    @Test
    void getConnection() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, null, null);
        }
    }

    @Test
    void closeResource() {
    }
}