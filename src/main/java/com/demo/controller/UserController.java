package com.demo.controller;

import com.demo.bean.LoginResult;
import com.demo.bean.ResultBean;
import com.demo.bean.user.UserInfo;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-11-15:17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method= RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public LoginResult login(@RequestBody UserInfo userInfo, @RequestHeader("openId") String openId){
        LoginResult i = userService.login(userInfo);
        return i;
    }
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces ={"application/json;charset=UTF-8"} )
    public ResultBean register(@RequestBody UserInfo userInfo){
        ResultBean result = new ResultBean();
        result.setSucResult("register success!");
        return result;
    }
    @RequestMapping(value = "/getUser1",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultBean getUser1() throws InterruptedException {
        ResultBean result = new ResultBean();
        Thread.sleep(100);
        result.setSucResult("register success!--100");
        return result;
    }
    @RequestMapping(value = "/getUser2",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResultBean getUser2() throws InterruptedException {
        ResultBean result = new ResultBean();
        Thread.sleep(5000);
        result.setSucResult("register success!--5000");
        return result;
    }
}
