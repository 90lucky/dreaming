package com.dreaming.model.bean;

import java.util.List;

/**
 *  Message:bean is defined for the request from web json , that not contact with db directly,
 *          so some Validation can be done
 *  Content:all the fields is consistent with web request , not meaning the same with db
 *          parameters
 *
 * create by lucky on 2017/12/8
 */
public class LoginBean {

    private String phone;
    private String userName;
    private String password;

    public List<UserBean> getUserList() {
        return userList;
    }

    public void setUserList(List<UserBean> userList) {
        this.userList = userList;
    }

    private List<UserBean> userList;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "phone='" + phone + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
