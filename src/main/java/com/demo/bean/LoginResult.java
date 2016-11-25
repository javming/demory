package com.demo.bean;

/**
 * 登录返回值实体
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-27-13:39
 */
public class LoginResult {
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        switch (code){
            case 0:this.message="用户名不存在！";
                break;
            case 1:this.message="密码错误！";
                break;
            case 2:this.message="登录成功！";
                break;
        }
    }

    public String getMessage() {
        return message;
    }

}
