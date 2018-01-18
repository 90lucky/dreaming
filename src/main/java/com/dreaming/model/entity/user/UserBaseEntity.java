package com.dreaming.model.entity.user;


import com.dreaming.model.entity.AbstractEntity;

/**
 *
 * create by lucky on 2017/12/8
 */
public class UserBaseEntity extends AbstractEntity {

    private static final String KEY_ID = "ID";
    private static final String KEY_USER_ID = "USER_ID";
    private static final String KEY_USER_NAME = "USER_NAME";
    private static final String KEY_PASSWORD = "USER_PASSWORD";
    private static final String KEY_USER_PHONE = "USER_PHONE";
    private static final String KEY_USER_MAIL = "USER_MAIL";
    private static final String KEY_CREATE_TIME = "CREATE_TIME";
    private static final String KEY_UPDATE_TIME = "UPDATE_TIME";
    private static final String KEY_LOGIN_TIME = "LOGIN_TIME";


    public UserBaseEntity(){
        put(KEY_TABLE,"t_sys_user_base");
        initEntity(this);
//        put(KEY_USER_ID,null);
//        put(KEY_USER_NAME,null);
//        put(KEY_PASSWORD,null);
//        put(KEY_USER_PHONE,null);
//        put(KEY_USER_MAIL,null);
//        put(KEY_CREATE_TIME,null);
//        put(KEY_UPDATE_TIME,null);
//        put(KEY_LOGIN_TIME,null);
    }

    private String id;
    private String userId;
    private String userName;
    private String password;
    private String phone;
    private String userMail;
    private String createTime;
    private String updateTime;
    private String loginTime;

    public Object getLoginTime() {
        return get(KEY_LOGIN_TIME);
    }

    public void setLoginTime(String loginTime) {
        put(KEY_LOGIN_TIME,loginTime);
    }

    public Object getUpdateTime() {
        return get(KEY_UPDATE_TIME);
    }

    public void setUpdateTime(String updateTime) {
        put(KEY_UPDATE_TIME,updateTime);
    }

    public Object getCreateTime() {
        return get(KEY_CREATE_TIME);
    }

    public void setCreateTime(String createTime) {
        put(KEY_CREATE_TIME,createTime);
    }

    public String getPhone() {
        return (String)get(KEY_USER_PHONE);
    }

    public void setPhone(String phone) {
        put(KEY_USER_PHONE,phone);
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        put(KEY_USER_MAIL,userMail);
    }

    public Object getPassword() {
        return get(KEY_PASSWORD);
    }

    public void setPassword(String password) {
        put(KEY_PASSWORD,password);
    }

    public Object getUserName() {
        return get(KEY_USER_NAME);
    }

    public void setUserName(String userName) {
        put(KEY_USER_NAME,userName);
    }

    public String getUserId() {
        return (String)get(KEY_USER_ID);
    }

    public void setUserId(String userId) {
        put(KEY_USER_ID,userId);
    }

    public Object getId() {
        return get(KEY_ID);
    }

    public void setId(String id) {
        put(KEY_ID,id);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + getId() + '\'' +
                ", userId='" + getUserId() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
