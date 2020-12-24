package com.github.niu.u.config;

import com.github.niu.u.common.core.R;
import com.github.niu.u.common.enums.ResultCode;
import com.github.niu.u.common.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/24 12:28 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/24 12:28 下午
 * @updateRemark:
 * @version: 1.0
 **/
@RestControllerAdvice
@ResponseBody
public class MvcExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(MvcExceptionHandler.class);
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e, HttpServletRequest request, HttpServletResponse response){

        logger.error("请求：{}发生异常：{}", request.getRequestURI(), e);

        return R.failed(e.getMessage());
    }
    @ExceptionHandler(BaseException.class)
    public Object handleBaseException(Exception e, HttpServletRequest request, HttpServletResponse response){

        logger.error("请求：{}发生异常：{}", request.getRequestURI(), e);

        return R.failed(e.getMessage());
    }

    /**
     * 自定义注解异常拦截
     */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})

    public Object handleMethodArgumentNotValidException(Exception e, HttpServletRequest request) {

        logger.error("请求：{}发生异常：{}", request.getRequestURI(), e);

        // 错误信息
        StringBuilder sb = new StringBuilder("参数校验失败：");
        // 错误信息map
        Map<String, String> error = new HashMap<>();

        String msg = "";
        if (!(e instanceof BindException) && !(e instanceof MethodArgumentNotValidException)) {
            for (ConstraintViolation cv: ((ConstraintViolationException)e).getConstraintViolations()) {
                msg = cv.getMessage();
                sb.append(msg).append("；");

                Iterator<Path.Node> it = cv.getPropertyPath().iterator();
                Path.Node last = null;
                while (it.hasNext()) {
                    last = (Path.Node)it.next();
                }
                /*for(last = null; it.hasNext(); last = (Path.Node)it.next()) {
                }*/
                error.put(last != null ? last.getName() : "", msg);
            }
        } else {
            List<ObjectError> allErrors = null;
            if (e instanceof BindException) {
                allErrors = ((BindException)e).getAllErrors();
            } else {
                allErrors = ((MethodArgumentNotValidException)e).getBindingResult().getAllErrors();
            }
            // 拼接错误信息
            for (ObjectError oe : allErrors) {
                msg = oe.getDefaultMessage();
                sb.append(msg).append("；");
                if (oe instanceof FieldError) {
                    error.put(((FieldError)oe).getField(), msg);
                } else {
                    error.put(oe.getObjectName(), msg);
                }
            }
        }
        R r =   R.failed(ResultCode.PARAM_IS_INVALID,sb.toString());
        r.setData(error);
        return  r;
    }
}
