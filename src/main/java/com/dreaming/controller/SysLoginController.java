package com.dreaming.controller;

import com.dreaming.exception.DreamingSysException;
import com.dreaming.base.ErrorCode;
import com.dreaming.base.Result;
import com.dreaming.bean.LoginBean;
import com.dreaming.entity.UserEntity;
import com.dreaming.service.login.ILoginQuery;
import com.dreaming.util.BeanUtil;
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
    private ILoginQuery loginSevice;
    @RequestMapping("/system/login")
    public Result Login(@RequestBody LoginBean loginBean) {
        try {
            UserEntity userEntity = BeanUtil.convertBean2Entity(loginBean,new UserEntity());
            if(!loginSevice.checkUserExist(userEntity));
            {
               return Result.error(ErrorCode.USER_NOT_EXIST,"user not exist");
            }
        } catch (DreamingSysException e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
