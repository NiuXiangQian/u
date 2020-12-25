package com.github.niu.u.controller;

import com.github.niu.u.common.CommonCache;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description: 短链接控制器
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/23 11:38 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/23 11:38 下午
 * @updateRemark:
 * @version: 1.0
 **/
@RestController
public class UController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @GetMapping("/u/{shortUrl}")
    public void u(HttpServletResponse response, @PathVariable String shortUrl) throws IOException {
        if (StringUtils.isNotBlank(shortUrl)){
           String url =  redisTemplate.opsForValue().get(CommonCache.SHORT_URL+shortUrl);
           if (StringUtils.isNotBlank(url))
               if (!(url.startsWith("http://")||url.startsWith("https://"))){
                url = "http://"+url;
               }
              response.sendRedirect(url);

            } else{
                response.getWriter().write("url is lose efficacy");
            }
        }

    }

