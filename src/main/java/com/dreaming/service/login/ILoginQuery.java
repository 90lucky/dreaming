package com.dreaming.service.login;

import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.service.IQuery;

/**
 * @author lucky
 * create on 2017/12/9
 */
public interface ILoginQuery extends IQuery {
    UserBaseEntity queryUserBase(UserBaseEntity userEntity) throws DreamingSysException;
}
