package com.itheima.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接工具类，保证在同一个线程中获取同一个数据库连接
 */
@Component
public class ConnectionUtils {
    /**
     * ThreadLocal保证的数据库连接
     */
    private final ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 获取当前线程上的数据库连接
     */
    public Connection getThreadConnection() {
        try {
            Connection connection = threadLocal.get();
            //没有连接，获取连接，并放置到ThreadLocal，获取到，直接返回
            if (connection == null) {
                connection = dataSource.getConnection();
                threadLocal.set(connection);
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        threadLocal.remove();
    }
}