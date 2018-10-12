package com.dreaming.service.login.impl;

import com.dreaming.base.ErrorCode;
import com.dreaming.base.ServerFlow;
import com.dreaming.base.ServerReturn;
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

    @Override
    public ServerReturn run(String id) throws DreamingSysException {

        //承接上文
        UserBaseEntity entity = (UserBaseEntity) ServerFlow.getContxt(id);

        UserBaseEntity result = queryUserBase(entity);

        result.setId(null);

        //准备下文
        ServerFlow.setContxt(id,result);

        return ServerReturn.SUCCESS;
    }
}
