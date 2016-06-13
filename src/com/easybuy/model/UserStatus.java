package com.easybuy.model;

/**
 * Created by lenovo on 2016/6/9.
 */
public enum UserStatus {
    NORMAL("普通用户", 1), ADMINISTRATOR("管理员", 2);

    private final String role;
    private final int index;

    UserStatus(String role, int index) {
        this.role = role;
        this.index = index;
    }

    public String getRole(){
        return this.role;
    }
}
