package com.dreaming.model.convert;

import com.dreaming.model.bean.LoginBean;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.util.BeanUtil;
import com.dreaming.util.ToolUtil;

import java.util.Date;

/**
 * Message:an util class for request bean convert to an entity,  which means different
 *         type or new field to do
 *
 * Content:all login request bean will convert to UserBaseEntity
 *
 * @author lucky
 * create on 2017/12/14
 */
public final class LoginBeanConvert {
    private LoginBeanConvert(){}

    /**
     * get UserBaseEntity from login bean,and update login time
     * @param loginBean
     * @return
     */
    public static UserBaseEntity getEntityForLogin(LoginBean loginBean){
        UserBaseEntity entity = BeanUtil.convertBean2Entity(loginBean,new UserBaseEntity());
//        entity.setLoginTime(ToolUtil.date2Str(new Date()));
        return entity;
    }

    /**
     * get UserBaseEntity from login bean,and add create time,user id
     * @param loginBean
     * @return
     */
    public static UserBaseEntity getEntityForRegister(LoginBean loginBean){
        UserBaseEntity entity = BeanUtil.convertBean2Entity(loginBean,new UserBaseEntity());
        entity.setCreateTime(ToolUtil.date2Str(new Date()));
        if (null == loginBean.getUserName())
        {
            entity.setUserName(loginBean.getPhone());
        }
        entity.setUserId(ToolUtil.getRandomUUID());

        return entity;
    }
}
