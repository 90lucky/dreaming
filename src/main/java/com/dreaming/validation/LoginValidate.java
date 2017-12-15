package com.dreaming.validation;

import com.dreaming.base.ErrorCode;
import com.dreaming.model.bean.LoginBean;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.util.ToolUtil;

/**
 *  Message：util for checkout request parameters ,sometimes not allowed null ,others also
 *           check if illegal
 *
 *  Content：check login all request parameters,different mean different rules
 *
 * @author lucky
 * create on 2017/12/14
 */
public final class LoginValidate {
    private LoginValidate()
    {}

    public static void checkLoginParam(LoginBean loginBean) throws DreamingSysException {
        if(null == loginBean)
        {
            throw new DreamingSysException(ErrorCode.BASE_BEAN_NULL,"request bean:{} not allowed null",
                    LoginBean.class.getName());
        }
        if(null == loginBean.getPhone() && null == loginBean.getUserName())
        {
            throw new DreamingSysException(ErrorCode.BASE_PARAM_NULL,"phone or username is needed",
                    LoginBean.class.getName());
        }
        if(!ToolUtil.checkIsMobile(loginBean.getPhone()))
        {
            throw new DreamingSysException(ErrorCode.LOGIN_PHONE_ILLEGAL,"phone is illegal");
        }
    }

    public static void checkRegisterParam(LoginBean loginBean) throws DreamingSysException {
        if(null == loginBean)
        {
            throw new DreamingSysException(ErrorCode.BASE_BEAN_NULL,"request bean:{} not allowed null",
                    LoginBean.class.getName());
        }
        if(null == loginBean.getPhone() && null == loginBean.getUserName())
        {
            throw new DreamingSysException(ErrorCode.BASE_PARAM_NULL,"phone or username is needed",
                    LoginBean.class.getName());
        }
        if(!ToolUtil.checkIsMobile(loginBean.getPhone()))
        {
            throw new DreamingSysException(ErrorCode.LOGIN_PHONE_ILLEGAL,"phone is illegal");
        }
    }
}
