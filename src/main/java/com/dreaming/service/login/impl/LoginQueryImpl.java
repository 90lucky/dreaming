package com.dreaming.service.login.impl;

import com.dreaming.dao.LoginDao;
import com.dreaming.entity.UserEntity;
import com.dreaming.service.login.ILoginQuery;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lucky
 * create on 2017/12/9
 */
@Service
public class LoginQueryImpl implements ILoginQuery {
    @Override
    public boolean checkUserExist(UserEntity userEntity) {

        Map result = LoginDao.getInstance(UserEntity.KEY_ID).queryUser(userEntity).get(0);


        return result==null||result.isEmpty();
    }
}
