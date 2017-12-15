package com.dreaming.service.login;

import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.ICreate;

/**
 * @author lucky
 * create on 2017/12/14
 */
public interface ILoginCreate extends ICreate {
    void createUserBase(UserBaseEntity entity) throws DreamingSysException;
}
