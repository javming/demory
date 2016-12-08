package com.demo.dao.impl;

import com.demo.bean.user.UserInfo;
import com.demo.dao.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-21-16:36
 */
@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SqlSessionTemplate sessionTemplate;

    public UserInfo selectByUsername(String username) {
        return null;
    }

    public void insertUser(UserInfo userInfo) {
        sessionTemplate.insert("com.demo.dao.UserDao.insertUser",userInfo);
    }

    public void deleteUser() {

    }

    public void updateUser(UserInfo userInfo) {

    }
}
