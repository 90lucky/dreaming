package com.dreaming.service.user.impl;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserInfoEntity;
import com.dreaming.service.user.IUserCreate;
import org.springframework.stereotype.Service;

/**
 * @author lucky
 * create on 2017/12/14
 */
@Service
public class UserCreateImpl implements IUserCreate {
    public void createUser(UserInfoEntity userEntity) throws DreamingSysException
    {
//        LoginDao.getInstance(UserInfoEntity.KEY_ID).createUser(userEntity);
    }
}
