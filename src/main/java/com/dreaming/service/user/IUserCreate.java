package com.dreaming.service.user;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.entity.user.UserInfoEntity;

/**
 * @author lucky
 * create on 2017/12/14
 */
public interface IUserCreate {
    void createUser(UserInfoEntity userEntity) throws DreamingSysException;
}
