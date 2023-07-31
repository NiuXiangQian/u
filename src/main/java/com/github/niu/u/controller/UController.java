package com.github.niu.u.controller;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.config.redis.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 短链接控制器
 *
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 11:38 下午
 **/
@RestController
public class UController {
    @Resource
    private RedisService redisService;

    @GetMapping("/u/{shortUrl}")
    public void u(HttpServletResponse response, @PathVariable String shortUrl) throws IOException {
        redisService.zIncrementScore(CommonCache.SHORT_URL, shortUrl);
        if (StringUtils.isNotBlank(shortUrl)) {
            String url = redisService.get(CommonCache.SHORT_URL + shortUrl);
            if (StringUtils.isNotBlank(url)) {
                if (!(url.startsWith("http://") || url.startsWith("https://"))) {
                    url = "http://" + url;
                }
                response.sendRedirect(url);
                return;
            }
        }
        response.getWriter().write("url is lose efficacy");
    }

}

