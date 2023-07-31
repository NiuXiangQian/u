package com.github.niu.u.common;

/**
 * 缓存
 *
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 9:05 下午
 **/
public interface CommonCache {

    /**
     * 短网址缓存前缀
     */
    String SHORT_URL = "u:short_url:";

    /**
     * Default
     * api访问ak_sk前缀key
     */
    String DEFAULT_AK_SK = "u:ak_sk:";

    /**
     * 访问统计
     */
    String ACCESS_COUNT = "u:access_count:zset";

}
