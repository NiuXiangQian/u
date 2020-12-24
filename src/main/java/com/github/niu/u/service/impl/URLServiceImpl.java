package com.github.niu.u.service.impl;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.common.util.MD5Util;
import com.github.niu.u.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/23 8:56 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/23 8:56 下午
 * @updateRemark:
 * @version: 1.0
 **/
@Service
public class URLServiceImpl implements URLService {

    @Value("${short_url.server:http://127.0.0.1:8080}")
    private String shortUrlServer;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public String generate(String srcUrl, Long valid) throws BaseException {
        if (valid == null){
            valid = 60L * 60L *4L ;  // 默认有效时间4个小时
        }

        if (valid!= -1 && valid <0){
            throw new BaseException("有效时间非法");
        }
       String shortURL =  MD5Util.encryptStr( srcUrl);
       String key = CommonCache.SHORT_URL+shortURL;
        redisTemplate.opsForValue().set(key,srcUrl);
       if (valid != -1){
           redisTemplate.expire(key,valid, TimeUnit.SECONDS);
       }
        return shortUrlServer+"/u/"+shortURL;
    }
}
