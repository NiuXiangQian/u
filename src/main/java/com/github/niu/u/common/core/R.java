package com.github.niu.u.common.core;


import com.github.niu.u.common.enums.ResultCode;

import java.io.Serializable;

/**
 * 响应类
 *
 * @author niuxiangqian
 * @since 2020/12/21 7:37 下午
 * @version 1.0
 **/
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;  //状态码
    private boolean success; //是否成功
    private String msg; //提示信息
    private T data; //数据

    public static <T> R<T> ok() {
        return restResult(null, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, ResultCode.SUCCESS.getCode(), msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, ResultCode.FAILED.getCode(), ResultCode.FAILED.getMsg());
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, ResultCode.FAILED.getCode(), msg);
    }

    public static <T> R<T> failed(ResultCode resultCode) {
        return restResult(null, resultCode.getCode(), resultCode.getMsg());
    }

    public static <T> R<T> failed(Integer code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, ResultCode.FAILED.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, ResultCode.FAILED.getCode(), msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setSuccess(code == 1);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }


}
