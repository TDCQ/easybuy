package com.easybuy.model;

/**
 * Created by lenovo on 2016/6/10.
 */
public enum Gender {
    male("男"), female("女"), neuter("中性");

    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGender(){
        return this.gender;
    }

}
