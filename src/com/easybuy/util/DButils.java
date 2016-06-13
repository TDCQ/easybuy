package com.easybuy.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by lenovo on 2016/6/10.
 */
public class DButils {
    private String driver;          // 数据库驱动
    private String url;             // 数据库连接url
    private String name;            // 数据库连接用户名
    private String password;        // 数据库连接密码

    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet resultSet;

    public DButils(){
        // 未指定参数则根据默认的的参数文件 创建连接实例。
        init();
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void init(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/dbconfig.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            name = properties.getProperty("name");
            password = properties.getProperty("password");

            Class.forName(driver);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection newConnection(){
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public int executeUpdate(String sql, Object... params){
        int affectedRows = 0;
        connection = newConnection();
        try {
            pstmt = connection.prepareStatement(sql);
            if(params!=null){
                for(int i=0; i<params.length; i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(connection);
        }
        return affectedRows;
    }

    /**
     * 执行查询语句
     * @param sql       // sql查询语句
     * @param params    // sql查询语句需要的参数
     * @return          // ResultSet 返回查询的结果集，需要再用完以后显示关闭connection, statement, resultSet
     */
    public ResultSet executeQuery(String sql, Object... params){
//        connection = newConnection();
        try {
            pstmt = connection.prepareStatement(sql);
            if(params!=null){
                for(int i=0; i<params.length; i++){
                    pstmt.setObject(i+1, params[i]);
                }
            }
            resultSet = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /**
     * 关闭数据库连接
     * @param conn
     */
    public void close(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭statement对象
     * @param stmt
     */
    public void close(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭resultSet对象
     * @param resultSet
     */
    public void close(ResultSet resultSet){
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPstmt() {
        return pstmt;
    }



}
