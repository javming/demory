package com.demo.bean;

import java.io.Serializable;
import java.util.UUID;

/**
 * 返回结果实体
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-28-9:47
 */
public class ResultBean implements Serializable{
    public String requestId = UUID.randomUUID().toString().replace("-", "");
    public String httpCode = "";
    public int code;
    public String message;
    public Object result = "";

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    /**
     *
     * @param status
     *            异常返回枚举类型
     */
    public void setFailMsg(SystemStatus status) {
        setCode(status.getCode());
        setMessage(status.getStr());
    }

    /**
     *
     * @param code
     * @param str
     *            异常返回枚举类型
     */
    public void setFailMsg(int code, String str) {
        setCode(code);
        setMessage(str);
    }

    /**
     *
     * @param result
     *            成功返回数据
     */
    public void setSucResult(Object result) {
        setCode(SystemStatus.OK.getCode());
        setMessage(SystemStatus.OK.getStr());
        setResult(result);
    }
}
