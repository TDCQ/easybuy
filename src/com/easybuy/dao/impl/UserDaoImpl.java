package com.easybuy.dao.impl;

import com.easybuy.dao.UserDao;
import com.easybuy.model.Gender;
import com.easybuy.model.User;
import com.easybuy.model.UserStatus;
import com.easybuy.util.DButils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/6/10.
 */
public class UserDaoImpl implements UserDao {
    /**
     * 添加用户
     * @param user
     * @return      // 返回添加用户的结果：true 成功， false失败
     */
    public boolean addUser(User user){
        DButils dButils = new DButils();
        boolean flag = false;
        String sql = "INSERT INTO user(name, password, gender, birthday, identityCode, " +
                "email, mobile, address, status) VALUES (?,?,?,?,?,?,?,?,?)";
        List userInfo = getUserInfo(user);
//        flag = dButils.executeUpdate(sql, userInfo) > 0;
        flag = dButils.executeUpdate(sql, userInfo.toArray()) > 0;
        return  flag;
    }

    /**
     * 删除用户
     * @param user
     * @return
     */
    public boolean delUser(User user){
        boolean flag = false;
        DButils dButils = new DButils();
        long id = user.getId();
        String sql = "DELETE FROM user WHERE id = ?";
        flag = dButils.executeUpdate(sql, id) > 0;
        return  flag;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean updateUser(User user){
        boolean flag = false;
        DButils dButils = new DButils();
        String sql = "Update user SET name=?, password=?, gender=?, birthday=?, identityCode=?," +
                "email=?, mobile=?, address=?, status=? WHERE id=?";
        List userInfo = getUserInfo(user);
        userInfo.add(user.getId());

        flag = dButils.executeUpdate(sql, userInfo.toArray()) > 0;
        return  flag;
    }

    /**
     * 根据电话号码获取用户
     * @param phoneNum
     * @return
     */
    public User getUserByMobile(String phoneNum){
        User user = new User();
        DButils dButils = new DButils();
        //language=MySQL
        String sql = "SELECT * FROM USER WHERE mobile=?";
        ResultSet rs = dButils.executeQuery(sql, phoneNum);
        try {
            if(rs.next()){
                updateUserFromResultSet(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;
    }

    /**
     * 根据邮箱获取用户
     * @param email
     * @return
     */
    public User getUserByEmail(String email){
        User user = new User();
        DButils dButils = new DButils();
        //language=MySQL
        String sql = "SELECT * FROM USER WHERE email=?";
        ResultSet rs = dButils.executeQuery(sql, email);
        try {
            if(rs.next()){
                updateUserFromResultSet(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;
    }

    /**
     * 根据日期获取生日在改日期的所有人
     * @param date
     * @return
     */
    public List<User> getUsersByBirthday(Date date){
        List<User> users = new ArrayList<User>();
        return users;
    }

    /**
     * 获取总的用户数目
     *
     * @return
     */
    @Override
    public int getUserNum() {
        DButils dButils = new DButils();
        String sql = "SELECT count(*) AS num FROM user";
        ResultSet rs = dButils.executeQuery(sql);
        int num = -1;
        try {
            if(rs.next()){
                num = rs.getInt("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 从给定用户对象中获取对象的具体信息组成ArrayList
     * @param user
     * @return
     */
    private List<Object> getUserInfo(User user){
        List<Object> userInfo = new ArrayList<Object>();
        String name = user.getName();
        String password = user.getPassword();
        String gender = user.getGender() !=null ? user.getGender().getGender() : Gender.neuter.getGender();
        Date birthday = user.getBirthday();
        String identityCode = user.getIdentityCode();
        String email = user.getEmail();
        String mobile = user.getMobile();
        String address = user.getAddress();
        String status = user.getStatus() != null? user.getStatus().getRole() : UserStatus.NORMAL.getRole();

        // ArrayList 是有序的，所有下面的userInfo.add() 不能乱变，会影响后面的sql语句的
        userInfo.add(name);
        userInfo.add(password);
        userInfo.add(gender);
        userInfo.add(birthday);
        userInfo.add(identityCode);
        userInfo.add(email);
        userInfo.add(mobile);
        userInfo.add(address);
        userInfo.add(status);

        return userInfo;
    }

    private void updateUserFromResultSet(ResultSet rs, User user){
        try {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String strGender = rs.getString("gender");
            Date birthday = rs.getDate("birthday");
            String identityCode = rs.getString("identityCode");
            String email = rs.getString("email");
            String mobile = rs.getString("mobile");
            Object strStatus = rs.getObject("status");
            user.setId(id);
            user.setName(name);
            user.setPassword(password);
            // 把从数据库中获取到的枚举字段(gender)转换成枚举类型
            for(Gender gender : Gender.values()){
                if(strGender.equals(gender.getGender())){
                    user.setGender(gender);
                }
            }
            if(user.getGender()==null){
                user.setGender(Gender.male);
            }
            user.setBirthday(birthday);
            user.setIdentityCode(identityCode);
            user.setEmail(email);
            user.setMobile(mobile);
            // 把从数据库中获取到的枚举字段(userStatus)转换成枚举类型
            for(UserStatus status : UserStatus.values()){
                if(strStatus.equals(status.getRole())){
                    user.setStatus(status);
                }
            }
            if(user.getStatus()== null){
                user.setStatus(UserStatus.NORMAL);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分页获取用户
     * @param pageSize  每页的容量
     * @param pageIndex     第几页
     * @return
     */
    @Override
    public List<User> getUsersPage(int pageSize, int pageIndex){
        List<User> users = new ArrayList<>();
        DButils dButils = new DButils();
        String sql = "SELECT * FROM user LIMIT ? OFFSET ?";

        ResultSet rs = dButils.executeQuery(sql, pageSize, pageIndex);
        try {
            while(rs.next()){
                User user = new User();
                updateUserFromResultSet(rs, user);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * 根据id获取用户
     *
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        User user = new User();
        DButils dButils = new DButils();
        //language=MySQL
        String sql = "SELECT * FROM USER WHERE id=?";
        ResultSet rs = dButils.executeQuery(sql, id);
        try {
            if(rs.next()){
                updateUserFromResultSet(rs, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;
    }
}
