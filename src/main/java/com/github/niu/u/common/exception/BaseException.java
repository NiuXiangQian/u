package com.github.niu.u.common.exception;

/**
 *  业务异常
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 9:15 下午
 **/
public class BaseException  extends RuntimeException{

    public BaseException(String message) {
        super(message);
    }
}
