package com.dreaming.controller;

import com.dreaming.base.Page;
import com.dreaming.base.Result;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.bean.LoginBean;
import com.dreaming.model.convert.LoginBeanConvert;
import com.dreaming.model.entity.user.UserBaseEntity;
import com.dreaming.service.login.LoginService;
import com.dreaming.validation.LoginValidate;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Message:The ACCESS of login from web or other rest request, which is started with spring boot
 *         Based template,something different maybe do next release
 * Content:the url of the request that must be sure about what and how ,also contains the parameters
 *         we don't need to assign which type of request to send
 * create by lucky on 2017/12/8
 */
@RestController
@RequestMapping("/system")
public class SysLoginController {
    private final static Logger logger = LoggerFactory.getLogger(SysLoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * the access for user login, user phone or name is needed and also with the password
     * @param loginBean user name,password or user phone password
     * @return if user is exist, return 200, else return the exception
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginBean loginBean) throws DreamingSysException {
        logger.info("[SysLoginController] login:",loginBean);
        UserBaseEntity resultEntity;
        try {
            LoginValidate.checkLoginParam(loginBean);
            UserBaseEntity queryEntity = LoginBeanConvert.getEntityForLogin(loginBean);
            resultEntity = loginService.queryLogin(queryEntity);
            List<UserBaseEntity> list = Lists.newArrayList(resultEntity);
            return Result.success("",new Page(),list);
        } catch (DreamingSysException e) {
            logger.error("[SysLoginController] login DreamingSysException:{}",e.getErrorMsg());
            throw e;
        } catch (Exception e){
            logger.error("[SysLoginController] login Exception:{}",e.getMessage());
            throw e;
        }

    }

    /**
     * the access for user register,phone num and also with password
     * @param loginBean phone num and also with password
     * @return rigist success or error with massage
     */
    @RequestMapping("/register")
    public Result register(@RequestBody LoginBean loginBean) throws DreamingSysException {
        try {
            LoginValidate.checkRegisterParam(loginBean);
            UserBaseEntity userEntity = LoginBeanConvert.getEntityForRegister(loginBean);
            loginService.createLogin(userEntity);
            return Result.success();
        } catch (DreamingSysException e) {
            logger.error("[SysLoginController] register DreamingSysException:{}",e.getErrorMsg());
            throw e;
        } catch (Exception e){
            logger.error("[SysLoginController] register Exception:{}",e.getMessage());
            throw e;
        }
    }


}
