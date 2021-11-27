package com.github.niu.u.config.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/***
 * redis操作
 * @author niuxiangqian
 * @version 1.0
 * @since 2021/11/27 11:22 上午
 **/
public interface RedisService {

    <T> void set(String key, T value);

    <T> void set(String key, T value, long expire, TimeUnit timeUnit);

    <T> T get(String key);

    boolean expire(String key, long expire);

    void del(String key);

    void delBatch(Set<String> keys);

    void delBatch(String keyPrefix);

    <T> void setList(String key, List<T> list);

    <T> void setList(String key, List<T> list, long expire, TimeUnit timeUnit);

    <T> List<T> getList(String key, Class<T> clz);

    boolean hasKey(String key);

    long getExpire(String key);

    Set<String> keySet(String keyPrefix);

    void hashPut(String key,String hashKey,Object value);

   <T> T hashGet(String key, String hashKey);
}
