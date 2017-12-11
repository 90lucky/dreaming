package com.dreaming.bean;

/**
 *  Message:bean is defined for the request from web json , that not contact with db directly,
 *          so some Validation can be done
 *  Content:all the fields is consistent with web request , not meaning the same with db
 *          parameters
 *
 * create by lucky on 2017/12/8
 */
public class LoginBean {
    private String userId;
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
