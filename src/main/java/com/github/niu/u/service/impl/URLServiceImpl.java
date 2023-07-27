package com.github.niu.u.service.impl;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.common.CommonConfig;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.common.util.MD5Util;
import com.github.niu.u.config.redis.RedisService;
import com.github.niu.u.model.vo.ShortUrlVo;
import com.github.niu.u.service.URLService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 8:56 下午
 **/
@Service
public class URLServiceImpl implements URLService {

    @Value("${short_url.server:http://127.0.0.1:8080}")
    private String shortUrlServer;
    @Resource
    private RedisService redisService;

    @Override
    public ShortUrlVo generate(String srcUrl, Long valid){
        if (valid == null) {
            valid = CommonConfig.DEFAULT_VALID_TIME;  // 默认有效时间3天
        }
        if (valid != -1 && valid < 0) {
            throw new BaseException("有效时间非法");
        }
        srcUrl = srcUrl.trim();
        String shortURL = MD5Util.encryptStr(srcUrl + System.currentTimeMillis());
        String key = CommonCache.SHORT_URL + shortURL;
        if (valid == -1) { // -1是永久有效 慎重
            redisService.set(key, srcUrl);
        } else {
            redisService.set(key, srcUrl, valid, TimeUnit.SECONDS);
        }
        ShortUrlVo shortUrlVo = new ShortUrlVo();
        shortUrlVo.setOrgUrl(srcUrl)
                .setShortTarget(shortURL)
                .setValidTime(valid)
                .setShortUrl(generateShortUrl(shortURL));

        return shortUrlVo;
    }

    @Override
    public ShortUrlVo restoreByTarget(String shortTarget){
        Objects.requireNonNull(shortTarget);
        String srcUrl = redisService.get(CommonCache.SHORT_URL + shortTarget);
        if (StringUtils.isBlank(srcUrl)) {
            throw new BaseException("短链接不存在或者已经失效");
        }
        Long validTime = redisService.getExpire(CommonCache.SHORT_URL + shortTarget);
        String shortUrl = generateShortUrl(shortTarget);
        ShortUrlVo shortUrlVo = new ShortUrlVo();
        shortUrlVo.setShortUrl(shortUrl)
                .setShortTarget(shortTarget)
                .setValidTime(validTime)
                .setOrgUrl(srcUrl);

        return shortUrlVo;
    }

    /**
     * 根据完整的短链接解析
     *
     * @param shortUrl:
     * @return com.github.niu.u.model.vo.ShortUrlVo
     * @todo 存在域名变更问题
     * @author nxq
     */
    @Override
    public ShortUrlVo restoreByShortUrl(String shortUrl){
        Objects.requireNonNull(shortUrl);
        if (!shortUrl.contains("/u/")) {
            throw new BaseException("短链接不合法");
        }
        String target = shortUrl.split("/u/")[1];

        return restoreByTarget(target);
    }

    private String generateShortUrl(String target) {
        return shortUrlServer + "/u/" + target;
    }
}
