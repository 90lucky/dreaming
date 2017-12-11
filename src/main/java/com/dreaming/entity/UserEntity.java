package com.dreaming.entity;


/**
 *
 * create by lucky on 2017/12/8
 */
public class UserEntity extends AbstractEntity {
    public static final String KEY_ID= "ID";
    public static final String KEY_USER_ID = "USER_ID";
    public static final String KEY_USER_NAME = "USER_NAME";
    public static final String KEY_PASSWORD = "USER_PASSWORD";
    private String id;
    private String userId;
    private String userName;
    private String password;

    public UserEntity(){
        put(KEY_TABLE,"t_sys_user_info");
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

    public Object getUserId() {
        return get(KEY_USER_ID);
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
