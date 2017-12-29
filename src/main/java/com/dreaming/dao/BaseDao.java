package com.dreaming.dao;

import com.dreaming.model.entity.AbstractEntity;
import com.dreaming.exception.DreamingSysException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Message:
 *
 * Content:
 *
 * @author lucky
 * create on 2017/12/11
 */
public class BaseDao {
    private final static String KEY_TABLE = "TABLE_NAME";
    private final static String DB_KEY_STR = "TABLE_KEY";
    private final static String DB_VALUE_STR = "TABLE_VALUE";
    private Map<String, String> paramMap = null;

    protected <T extends AbstractEntity> void create(Connection conn, T entity) throws SQLException, DreamingSysException {
        paramMap = getCreateParams(entity);
        StringBuilder stringBuilder = new StringBuilder("insert into ");
        stringBuilder.append(entity.get(KEY_TABLE)).append("(").append(paramMap.get(DB_KEY_STR)).append(") values (")
                .append(paramMap.get(DB_VALUE_STR)).append(")");
        PreparedStatement psd = conn.prepareStatement(stringBuilder.toString());
        psd.executeUpdate();
        psd.close();
    }

    protected   <T extends AbstractEntity> List<T> queryAll(Connection conn,T entity) throws SQLException, IllegalAccessException, InstantiationException {
        StringBuilder stringBuilder = new StringBuilder("select * from ");
        stringBuilder.append(entity.get(KEY_TABLE)).append(" where 1=1 ").append(getQueryParams(entity));

        List<T> resultList = new ArrayList<>();
        T result ;
        PreparedStatement psd = conn.prepareStatement(stringBuilder.toString());
        ResultSet rs = psd.executeQuery();

        while (rs.next())
        {
            result = (T) entity.getClass().newInstance();
            for(String key:getKeys(entity))
            {
                result.put(key,rs.getString(key));
            }
            resultList.add(entity);
        }
        rs.close();
        psd.close();
        return resultList;
    }

    protected   <T extends AbstractEntity> T queryOne(Connection conn,T entity) throws SQLException, IllegalAccessException, InstantiationException {
        StringBuilder stringBuilder = new StringBuilder("select * from ");
        stringBuilder.append(entity.get(KEY_TABLE)).append(" where 1=1 ").append(getQueryParams(entity));
        T result = null;
        PreparedStatement psd = conn.prepareStatement(stringBuilder.toString());
        ResultSet rs = psd.executeQuery();

        while (rs.next())
        {
            result = (T) entity.getClass().newInstance();
            for(String key:getKeys(entity))
            {
                result.put(key,rs.getString(key));
            }
        }
        rs.close();
        psd.close();
        return result;
    }

    private <T extends AbstractEntity> List<String> getKeys(T entity)
    {
        List<String> strList = new ArrayList<>();
        for (String key : entity.keySet()) {
            if (!KEY_TABLE.equals(key)) {
                strList.add(key);
            }
        }
        return strList;
    }

    private <T extends AbstractEntity> String getQueryParams(T entity)
    {
        String condition = "";
        if (entity.isEmpty()) {
            return condition;
        }
        for (String key : entity.keySet()) {
            if (!KEY_TABLE.equals(key) && null != entity.get(key)) {
               condition = condition + " and "+ key + " = '" + entity.get(key)+"' ";
            }
        }
        return condition;
    }


    private Map<String, String> getCreateParams(AbstractEntity entity) {
        Map<String, String> map = new HashMap<>();
        if (entity.isEmpty()) {
            return map;
        }
        String keyStr = "";
        String valueStr = "";
        for (String key : entity.keySet()) {
            if (!KEY_TABLE.equals(key) && null !=entity.get(key)) {
                keyStr = keyStr + key + ",";
                valueStr = valueStr +"'"+ entity.get(key) + "',";
            }
        }
        map.put(DB_KEY_STR,keyStr.substring(0,keyStr.length()-1));
        map.put(DB_VALUE_STR,valueStr.substring(0,valueStr.length()-1));
        return map;
    }


}
