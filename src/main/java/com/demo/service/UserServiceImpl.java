package com.demo.service;

import com.demo.bean.LoginResult;
import com.demo.bean.user.UserInfo;
import com.demo.dao.UserDao;
import com.demo.util.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-20-13:35
 */
@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Transactional
    public void register(UserInfo userInfo) {
        logger.info("userInfo=="+userInfo);
        userInfo.setPassword(MD5.getMD5(userInfo.getPassword()));
        userDao.insertUser(userInfo);

    }

    public LoginResult login(UserInfo userInfo) {
        logger.info("userInfo=="+userInfo);
        LoginResult lrb = new LoginResult();
        UserInfo userInfo1 = userDao.selectByUsername(userInfo.getUsername());
        String password = MD5.getMD5(userInfo.getPassword());
        if (userInfo1 == null) {
            lrb.setCode(0);
        }else if(!userInfo1.getPassword().equals(password)){
            lrb.setCode(1);
        }else {
            lrb.setCode(2);
        }
        return lrb;
    }
}
