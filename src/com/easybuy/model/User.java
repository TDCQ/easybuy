package com.easybuy.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by lenovo on 2016/6/8.
 */
public class User implements Serializable {
    private Long id;                    // 编号
    private String name;                // 姓名
    private String password;            // 密码
    private Gender gender;              // 性别
    private Date birthday;            // 出生日期
    private String identityCode;        // 身份证号码
    private String email;               // 邮箱
    private String mobile;              // 手机号
    private String address;             // 地址
    private UserStatus status;          // 身份级别， 1、普通用户， 2、管理员

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Map<String, Object> getSelfDescUser(){
        Map<String, Object> aboutMe = new HashMap<>();
        aboutMe.put("id", id);
        aboutMe.put("name", name);
        aboutMe.put("password", password);
        aboutMe.put("gender", gender);
        aboutMe.put("birthday", birthday );
        aboutMe.put("identityCode", identityCode);
        aboutMe.put("email", email);
        aboutMe.put("mobile", mobile);
        aboutMe.put("address", address);
        aboutMe.put("status", status);
        
        return aboutMe;
    }
}
