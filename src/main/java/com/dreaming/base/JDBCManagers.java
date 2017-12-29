package com.dreaming.base;

import com.dreaming.dao.pool.BaseSysConPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class JDBCManagers {

    private static final Map<String,LinkedList<Connection>> JDBC_TRANS = new HashMap<>();

    public static void addConn2Trans(String id,Connection conn)
    {
        if(JDBC_TRANS.containsKey(id))
        {
            JDBC_TRANS.get(id).add(conn);
        }
        else {
            LinkedList<Connection> list = new LinkedList<>();
            list.add(conn);
            JDBC_TRANS.put(id,list);
        }
    }

    /**
     * 提交流域中所有事务
     * @param id
     */
    public static void commit(String id){
        for (Connection conn:getConnList(id)) {
            try {
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        removeKey(id);
    }

    /**
     * 回滚流域中所有的事务
     * @param id
     */
    public static void rollback(String id)
    {
        for (Connection conn:getConnList(id)) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        removeKey(id);
    }

    private static LinkedList<Connection> getConnList(String id){
        if(!JDBC_TRANS.containsKey(id))
        {
            return new LinkedList<>();
        }
        JDBC_TRANS.get(id).clear();
        return JDBC_TRANS.get(id);
    }

    private static void removeKey(String id){
        if(JDBC_TRANS.containsKey(id))
        {
            for (Connection connection : JDBC_TRANS.get(id)) {
                BaseSysConPool.release(connection);
            }
            //release all of the connection list
            JDBC_TRANS.get(id).clear();

            JDBC_TRANS.remove(id);
        }
    }


}
