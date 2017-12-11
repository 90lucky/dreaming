package com.dreaming.dao;

import com.dreaming.dao.pool.BaseSysConPool;
import com.dreaming.entity.UserEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author lucky
 * create on 2017/12/9
 */
public class LoginDao extends BaseDao {
    private static LoginDao loginDao = new LoginDao();
    private static Connection conn;
    private static String gid;
    private LoginDao(){}
    public static LoginDao getInstance(String id){
        gid = id;
        conn = BaseSysConPool.getConnection(gid);
        return loginDao;
    }

    public List<UserEntity> queryUser(UserEntity userEntity){
        List<UserEntity> list = null;
        try {
            list = queryAll(conn,userEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        BaseSysConPool.release(gid,conn);
        return list;
    }

}
