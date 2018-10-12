package com.dreaming.service.user.impl;

import com.dreaming.base.ServerFlow;
import com.dreaming.base.ServerReturn;
import com.dreaming.dao.LoginDao;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.model.entity.user.UserInfoEntity;
import com.dreaming.service.user.IUserCreate;
import org.springframework.stereotype.Service;

/**
 * @author lucky
 * create on 2017/12/14
 */
@Service
public class UserCreateImpl implements IUserCreate {
    public void createUser(String id, UserBaseEntity userEntity) throws DreamingSysException {
        LoginDao.getInstance(id).createUser(userEntity);
    }

    @Override
    public ServerReturn run(String id) throws DreamingSysException {
        //承接上文
        UserBaseEntity entity = (UserBaseEntity) ServerFlow.getContxt(id);

//        entity.setId("5");

        createUser(id, entity);

        ServerFlow.setContxt(id,entity);

        return ServerReturn.NEXT;
    }
}
