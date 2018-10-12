package com.dreaming.service.user;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.model.entity.user.UserInfoEntity;
import com.dreaming.service.ICreate;

/**
 * @author lucky
 * create on 2017/12/14
 */
public interface IUserCreate extends ICreate{
    void createUser(String id,UserBaseEntity userEntity) throws DreamingSysException;
}
