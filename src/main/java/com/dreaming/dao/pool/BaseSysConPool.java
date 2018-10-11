package com.dreaming.dao.pool;

import com.dreaming.base.JDBCManagers;
import com.dreaming.util.ToolUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Message:base pool is initialized with some connection from the databases we defined,
 *         we can get a long connection here which don't need to create frequently
 *
 * Content:initialize the connection,get the connection and release the connection
 *
 * @author lucky
 * create on 2017/12/9
 */
public class BaseSysConPool {

    /**
     * 最大连接数
     */
    private static final int COUNT = 20;
    /**
     * 存放数据库
     */
    private static final LinkedList<Connection> connections = new LinkedList<>();
    /**
     * 创建锁
     */
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition notEmpty = lock.newCondition();
    private static final Condition notFull = lock.newCondition();
    /**
     * 初始化信息
     */
    static {
        for (int i = 0; i < COUNT; i++) {
            connections.add(createConnection());
        }
    }

    private static Connection createConnection()
    {
        Connection conn=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.13.94:3306/test" , "root", "123456" );
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获取Connection
     */
    public static Connection getConnection(String id) {
        final ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            if (connections.isEmpty()) {
                notEmpty.await();
            }
            Connection connection = connections.removeFirst();
            //非查询的链接，开启事务管理
            if (!ToolUtil.strIsBlanck(id))
            {
                connection.setAutoCommit(false);
                JDBCManagers.addConn2Trans(id,connection);
            }
            notFull.signalAll();
            return connection;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return null;
    }

    /**
     * 释放连接
     *
     * @param connection
     */
    public static void release(Connection connection) {
        final ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            if (connections.size() == COUNT) {
                notFull.await();
            }
            if (connection == null || connection.isClosed()) {
                connections.add(createConnection());
                notEmpty.signalAll();
                return;
            }
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            connections.add(connection);
            notEmpty.signalAll();
        } catch (InterruptedException|SQLException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
