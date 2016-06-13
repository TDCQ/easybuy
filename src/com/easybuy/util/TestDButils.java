package com.easybuy.util;

import com.easybuy.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by lenovo on 2016/6/12.
 */
public class TestDButils {
    public static void main(String[] args){
        DButils dButils = new DButils();
        ResultSet resultSet = dButils.executeQuery("SELECT * FROM user");
        if(resultSet!=null){
            try {
                if(resultSet.next()){
                    System.out.println(resultSet.getObject("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
