package com.dreaming.service.login.impl;

import com.dreaming.base.ErrorCode;
import com.dreaming.dao.LoginDao;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.login.ILoginQuery;
import org.springframework.stereotype.Service;

/**
 * Message:business implement handlers,maybe transaction start here
 *
 * Content:login query,also need update login time
 *
 * @author lucky
 * create on 2017/12/9
 */
@Service
public class LoginQueryImpl implements ILoginQuery {
    @Override
    public UserBaseEntity queryUserBase(UserBaseEntity entity) throws DreamingSysException {

        UserBaseEntity userBaseEntity = LoginDao.getInstance(null).queryUserBase(entity);

        if(null == userBaseEntity)
        {
            throw new DreamingSysException(ErrorCode.LOGIN_NOT_EXIST,"{} not exist",
                    (String)(null == entity.getUserName()? entity.getPhone():entity.getUserName()));
        }
        return userBaseEntity;
    }
}
