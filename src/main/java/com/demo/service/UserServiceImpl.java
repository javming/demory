package com.demo.service;

import com.demo.bean.LoginResult;
import com.demo.bean.user.UserInfo;
import com.demo.dao.UserDao;
import com.demo.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-20-13:35
 */
@Service
public class UserServiceImpl extends BasicService implements UserService{
    //private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Transactional
    public void register(UserInfo userInfo) {
        log.info("userInfo=="+userInfo);
        userInfo.setPassword(MD5.getMD5(userInfo.getPassword()));
        userDao.insertUser(userInfo);

    }

    public LoginResult login(UserInfo userInfo) {
        log.info("userInfo=="+userInfo);
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
