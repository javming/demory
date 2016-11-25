package com.demo.controller;

import com.demo.bean.LoginResult;
import com.demo.bean.ResultBean;
import com.demo.bean.user.UserInfo;
import com.demo.rabbitmq.Producer;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-11-15:17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Producer producer;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method= RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultBean login(@RequestBody UserInfo userInfo){
        LoginResult i = userService.login(userInfo);
        System.out.println(i);
        ResultBean result = new ResultBean();
        result.setSucResult("login success!");
        return result;

    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces ={"application/json;charset=UTF-8"} )
    public ResultBean register(@RequestBody UserInfo userInfo){
        userService.register(userInfo);
        ResultBean result = new ResultBean();
        result.setSucResult("register success!");
        return result;
    }

}
