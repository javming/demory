package com.demo.service;

import com.demo.bean.LoginResult;
import com.demo.bean.user.UserInfo;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-20-13:30
 */
public interface UserService {

    void register(UserInfo userInfo);

    LoginResult login(UserInfo userInfo);
}
