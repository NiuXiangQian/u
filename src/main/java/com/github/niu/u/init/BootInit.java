package com.github.niu.u.init;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.config.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 12:17 下午
 **/
@Component
public class BootInit implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(BootInit.class);
    @Resource
    private RedisService redisService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //先放入一个可用的ak sk
        //后期版本进行丰富此功能
        redisService.hashPut(CommonCache.DEFAULT_AK_SK, "ak_123", "sk_123");
        logger.info("default access api ak and sk is {ak_123},{sk_123}");

    }
}
