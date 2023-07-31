package com.github.niu.u.service.impl;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.config.redis.RedisService;
import com.github.niu.u.service.ApiAccessService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 12:05 下午
 **/
@Service
public class DefaultApiAccessServiceImpl implements ApiAccessService {
    @Resource
    private RedisService redisService;

    @Override
    public boolean acceptAccess(String ak, String sk) {
        if (StringUtils.isEmpty(ak)|| StringUtils.isEmpty(sk)){
            return false;
        }
        String accSk= redisService.hashGet(CommonCache.DEFAULT_AK_SK,ak);
        return accSk!=null && accSk.equals(sk);
    }
}
