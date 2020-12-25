package com.github.niu.u.service.impl;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.common.util.MD5Util;
import com.github.niu.u.model.vo.ShortUrlVo;
import com.github.niu.u.service.URLService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
    public ShortUrlVo generate(String srcUrl, Long valid) throws BaseException {
        if (valid == null){
            valid = 60L * 60L *4L ;  // 默认有效时间4个小时
        }
        if (valid!= -1 && valid <0){
            throw new BaseException("有效时间非法");
        }
        srcUrl = srcUrl.trim();
       String shortURL =  MD5Util.encryptStr( srcUrl);
       String key = CommonCache.SHORT_URL+shortURL;
        redisTemplate.opsForValue().set(key,srcUrl);
       if (valid != -1){ // -1是永久有效
           redisTemplate.expire(key,valid, TimeUnit.SECONDS);
       }
        ShortUrlVo shortUrlVo = new ShortUrlVo();
       shortUrlVo.setOrgUrl(srcUrl);
       shortUrlVo.setShortTarget(shortURL);
       shortUrlVo.setValidTime(valid);
       shortUrlVo.setShortUrl(generateShortUrl(shortURL));

        return shortUrlVo;
    }

    @Override
    public ShortUrlVo restoreByTarget(String shortTarget) throws BaseException {
        Objects.requireNonNull(shortTarget);
        String srcUrl =  redisTemplate.opsForValue().get(CommonCache.SHORT_URL+shortTarget);
        if (StringUtils.isBlank(srcUrl)){
             throw new BaseException("短链接不存在或者已经失效");
        }
       Long validTime =  redisTemplate.getExpire(CommonCache.SHORT_URL+shortTarget);
       String shortUrl = generateShortUrl(shortTarget);
       ShortUrlVo shortUrlVo =new ShortUrlVo();
       shortUrlVo.setShortUrl(shortUrl)
               .setShortTarget(shortTarget)
               .setValidTime(validTime)
               .setOrgUrl(srcUrl);

        return shortUrlVo;
    }

    /**
     * 根据完整的短链接解析
     * @todo 存在域名变更问题
     * @author nxq
     * @param shortUrl:
     * @return com.github.niu.u.model.vo.ShortUrlVo
     */
    @Override
    public ShortUrlVo restoreByShortUrl(String shortUrl) throws BaseException {
        Objects.requireNonNull(shortUrl);
        if (!shortUrl.contains("/u/")){
            throw new BaseException("短链接不合法");
        }
        String target = shortUrl.split("/u/")[1];

        return restoreByTarget(target);
    }
    private String generateShortUrl(String target){
        return shortUrlServer+"/u/"+target;
    }
}
