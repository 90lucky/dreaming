package com.dreaming.service.login.impl;

import com.dreaming.base.ErrorCode;
import com.dreaming.base.ServerFlow;
import com.dreaming.base.ServerReturn;
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
//        UserBaseEntity userBaseEntity = LoginDao.getInstance(null).queryByPhone(entity.getPhone());
//        if(null != userBaseEntity)
//        {
//            throw new DreamingSysException(ErrorCode.LOGIN_ALREADY_EXIST, "phone:{} already exist",entity.getPhone());
//        }
//        LoginDao.getInstance(entity.getUserId()).createUser(entity);
    }

    @Override
    public ServerReturn run(String id) throws DreamingSysException {
        try {
            //承接上文
            UserBaseEntity entity = (UserBaseEntity) ServerFlow.getContxt(id);

            UserBaseEntity userBaseEntity = LoginDao.getInstance(null).queryByPhone(entity.getPhone());
            if(null != userBaseEntity)
            {
                throw new DreamingSysException(ErrorCode.LOGIN_ALREADY_EXIST, "phone:{} already exist",entity.getPhone());
            }
            LoginDao.getInstance(id).createUser(entity);

            //准备下文
            ServerFlow.setContxt(id,entity);
        } catch (DreamingSysException e) {
           throw e;
        }
        return ServerReturn.SUCCESS;
    }


}
