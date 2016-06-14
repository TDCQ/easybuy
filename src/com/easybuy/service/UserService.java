package com.easybuy.service;

import com.easybuy.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/6/10.
 */
public interface UserService {
    /**
     * 添加用户
     * @param user
     * @return      // 返回添加用户的结果：true 成功， false失败
     */
    public boolean addUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    public boolean delUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public boolean updateUser(User user);

    /**
     * 根据电话号码获取用户
     * @param phoneNum
     * @return
     */
    public User getUserByMobile(String phoneNum);

    /**
     * 根据邮箱获取用户
     * @param email
     * @return
     */
    public User getUserByEmail(String email);

    /**
     * 根据日期获取生日在改日期的所有人
     * @param date
     * @return
     */
    public List<User> getUsersByBirthday(Date date);

    /**
     * 根据账号（邮箱或手机）和密码获取用户
     * @param loginName
     * @param password
     * @return
     */
    public User login(String loginName, String password);

    /**
     * 查询数据库中总共有多少用户
     * @return
     */
    public int getUserNum();

    /**
     * 分页获取用户
     * @param PageSize  每页的容量
     * @param PageIndex     第几页
     * @return
     */
    public List<User> getUsersPage(int PageSize, int PageIndex);

    /**
     * 根据传入的id查找用户
     * @param id
     * @return 用户
     */
    public User getUserById(String id);
}
