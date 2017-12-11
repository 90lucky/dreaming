package com.dreaming.test;

import com.dreaming.dao.BaseDao;
import com.dreaming.entity.UserEntity;

import java.sql.SQLException;

/**
 * @author lucky
 * create on 2017/12/11
 */
public class TestMain {
    public static void main(String[] args)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("111");
        userEntity.setPassword("password");
        userEntity.setUserId("user_id");
        userEntity.setUserName("user_name");
        System.out.println(userEntity);

        BaseDao baseDao = new BaseDao();

    }
}
