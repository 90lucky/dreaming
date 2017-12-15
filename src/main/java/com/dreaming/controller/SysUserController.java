package com.dreaming.controller;

import com.dreaming.base.Result;
import com.dreaming.exception.DreamingSysException;
import com.dreaming.model.bean.UserBean;
import com.dreaming.model.entity.user.UserInfoEntity;
import com.dreaming.service.user.IUserCreate;
import com.dreaming.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lucky
 * create on 2017/12/14
 */
@RestController
public class SysUserController {

    @Autowired
    private IUserCreate userCreate;

    @RequestMapping("/system/user/add")
    public Result Login(@RequestBody UserBean userBean) {
        try {
            UserInfoEntity userEntity = BeanUtil.convertBean2Entity(userBean,new UserInfoEntity());
            userCreate.createUser(userEntity);
        } catch (DreamingSysException e) {
            e.printStackTrace();
        }
        return Result.success();
    }
}
