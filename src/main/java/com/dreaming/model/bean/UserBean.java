package com.dreaming.model.bean;

/**
 * @author lucky
 * create on 2017/12/14
 */
public class UserBean {
    private String userId;
    private String userName;
    private String password;
    private String idCard;
    private String address;
    private String birthday;
    private String phone;
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    @Override
    public String toString() {
        return "UserBean{" +
                "userId='" + getUserId() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", idCard='" + getIdCard() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", birthday='" + getBirthday() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", mail='" + getMail() + '\'' +
                '}';
    }
}
