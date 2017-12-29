package com.dreaming.controller;

import com.dreaming.base.Result;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.bean.LoginBean;
import com.dreaming.model.convert.LoginBeanConvert;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.service.login.LoginService;
import com.dreaming.validation.LoginValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Message:The ACCESS of login from web or other rest request, which is started with spring boot
 *         Based template,something different maybe do next release
 * Content:the url of the request that must be sure about what and how ,also contains the parameters
 *         we don't need to assign which type of request to send
 * create by lucky on 2017/12/8
 */
@RestController
public class SysLoginController {
    @Autowired
    private LoginService loginService;

    /**
     * the access for user login, user phone or name is needed and also with the password
     * @param loginBean user name,password or user phone password
     * @return if user is exist, return 200, else return the exception
     */
    @RequestMapping("/system/login")
    public Result login(@RequestBody LoginBean loginBean) {
        UserBaseEntity resultEntity;
        try {
            LoginValidate.checkLoginParam(loginBean);

            UserBaseEntity queryEntity = LoginBeanConvert.getEntityForLogin(loginBean);

            resultEntity = loginService.queryLogin(queryEntity);

            //通过验证后，更新登陆时间,可将信息存于消息队列，异步更新
//            loginUpdateService.updateUserBase(resultEntity);
        } catch (DreamingSysException e) {
            e.printStackTrace();
            return Result.error(e.getErrorCode(),e.getErrorMsg());
        }
        return Result.success(resultEntity);
    }

    /**
     * the access for user register,phone num and also with password
     * @param loginBean phone num and also with password
     * @return rigist success or error with massage
     */
    @RequestMapping("/system/register")
    public Result regist(@RequestBody LoginBean loginBean) {
        try {
            LoginValidate.checkRegisterParam(loginBean);
            UserBaseEntity userEntity = LoginBeanConvert.getEntityForRegister(loginBean);
            loginService.createLogin(userEntity);
        } catch (DreamingSysException e) {
            e.printStackTrace();
            return Result.error(e.getErrorCode(),e.getErrorMsg());
        }
        return Result.success();
    }


}
