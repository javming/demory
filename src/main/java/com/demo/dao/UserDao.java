package com.demo.dao;

import com.demo.bean.user.UserInfo;

/**
 * Created by Administrator on 2016/10/19.
 */
public interface UserDao {
    UserInfo selectByUsername(String username);
    void insertUser(UserInfo userInfo);
    void deleteUser();
    void updateUser(UserInfo userInfo);
}
