package com.github.niu.u.service.impl;

import com.github.niu.u.common.CommonCache;
import com.github.niu.u.service.ApiAccessService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 12:05 下午
 **/
@Service
public class DefaultApiAccessServiceImpl implements ApiAccessService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean acceptAccess(String ak, String sk) {
        if (StringUtils.isEmpty(ak)|| StringUtils.isEmpty(sk))
            return false;
        Object accSk= redisTemplate.opsForHash().get(CommonCache.DEFAULT_Ak_SK,ak);
        return accSk!=null && accSk.equals(sk);
    }
}
