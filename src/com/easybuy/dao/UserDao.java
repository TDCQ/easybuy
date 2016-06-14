package com.easybuy.dao;

import com.easybuy.model.User;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/6/10.
 */
public interface UserDao {
    /**
     * 添加用户
     * @param user  // 传入的user Model
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
     * 获取总的用户数目
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
     * 根据id获取用户
     * @param id
     * @return
     */
    public User getUserById(Long id);
}
