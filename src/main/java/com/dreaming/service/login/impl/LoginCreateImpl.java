package com.dreaming.service.login.impl;

import com.dreaming.base.ErrorCode;
import com.dreaming.dao.LoginDao;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.login.ILoginCreate;
import org.springframework.stereotype.Service;

/**
 * @author lucky
 * create on 2017/12/14
 */
@Service
public class LoginCreateImpl implements ILoginCreate {

    @Override
    public void createUserBase(UserBaseEntity entity) throws DreamingSysException {
        UserBaseEntity userBaseEntity = LoginDao.getInstance(null).queryByPhone(entity.getPhone());
        if(null != userBaseEntity)
        {
            throw new DreamingSysException(ErrorCode.LOGIN_ALREADY_EXIST, "phone:{} already exist",entity.getPhone());
        }
        LoginDao.getInstance(entity.getUserId()).createUser(entity);
    }
}
