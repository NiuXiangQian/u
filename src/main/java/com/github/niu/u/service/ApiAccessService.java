package com.github.niu.u.service;

/**
 * api访问控制
 *
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 12:02 下午
 **/
public interface ApiAccessService {
    /**
     * 是否允许访问api
     *
     * @param ak:
     * @param sk:
     * @return boolean
     * @author nxq
     */
    boolean acceptAccess(String ak, String sk);
}
