package com.dreaming.service.login;

import com.dreaming.entity.UserEntity;
import com.dreaming.service.IQuery;

/**
 * @author lucky
 * create on 2017/12/9
 */
public interface ILoginQuery extends IQuery {
    boolean checkUserExist(UserEntity userEntity);
}
