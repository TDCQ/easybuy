package com.easybuy.service.impl;

import com.easybuy.dao.UserDao;
import com.easybuy.dao.impl.UserDaoImpl;
import com.easybuy.model.User;
import com.easybuy.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/6/10.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    /**
     * 添加用户
     *
     * @param user
     * @return // 返回添加用户的结果：true 成功， false失败
     */
    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @Override
    public boolean delUser(User user) {
        return false;
    }

    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(User user) {
        return false;
    }

    /**
     * 根据电话号码获取用户
     *
     * @param phoneNum
     * @return
     */
    @Override
    public User getUserByMobile(String phoneNum) {
        return null;
    }

    /**
     * 根据邮箱获取用户
     *
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    /**
     * 根据日期获取生日在改日期的所有人
     *
     * @param date
     * @return
     */
    @Override
    public List<User> getUsersByBirthday(Date date) {
        return null;
    }

    /**
     * 根据邮箱和密码获取用户
     *
     * @param email
     * @param password
     * @return
     */
    private User getUserByEmailAndPassword(String email, String password) {
        User user = userDao.getUserByEmail(email);
        if(user!=null && password.equals(user.getPassword())){
            return user;
        }
        return null;
    }

    /**
     * 根据手机号和密码获取用户
     *
     * @param mobile
     * @param password
     * @return
     */
    private User getUserByMobileAndPassword(String mobile, String password) {
        User user = userDao.getUserByMobile(mobile);
        if(user!=null && password.equals(user.getPassword())){
            return user;
        }
        return null;
    }

    /**
     * 根据登陆名和密码获取用户
     * @param loginName
     * @param password
     * @return
     */
    @Override
    public User login(String loginName, String password){
        User user = getUserByEmailAndPassword(loginName, password);
        if(user==null){
            user = getUserByMobileAndPassword(loginName, password);
        }
        return user;
    }
}
