package com.github.niu.u.interceptor;

import com.alibaba.fastjson.JSON;
import com.github.niu.u.common.core.R;
import com.github.niu.u.service.ApiAccessService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  api拦截器
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 3:33 下午
 **/
@Component
public class ApiAccessInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(ApiAccessInterceptor.class);


    @Autowired
    private ApiAccessService apiAccessService;

    /**
     * 访问接口之前拦截
     * 可以做登陆判断、访问权限判断、记录日志。。。
     * 方法return true是允许访问接口  return false是拒绝访问
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //检查请求头和请求参数中是否有ak sk
       String  ak = request.getHeader("ak");
       String  sk = request.getHeader("sk");
       if (StringUtils.isEmpty(ak))
           ak = request.getParameter("ak");
       if (StringUtils.isEmpty(sk))
           sk = request.getParameter("sk");


       if (StringUtils.isEmpty(ak) || StringUtils.isEmpty(sk)){
           returnJson(response, JSON.toJSONString( R.failed("ak sk  not in header or parameter")));
            return false;
        }
       //检查是否有访问api的权限
        boolean acc =  apiAccessService.acceptAccess(ak,sk);
       if (!acc){
           returnJson(response, JSON.toJSONString( R.failed("对不起，您没有权限访问，请检查ak 和 sk")));
       }
        return acc; //放行所有接口

    }
    private void returnJson(HttpServletResponse response, String json) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error", e);
        }
    }

}
