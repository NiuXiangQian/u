package com.github.niu.u.service;

/**
 * @description: api访问控制
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/24 12:02 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/24 12:02 下午
 * @updateRemark:
 * @version: 1.0
 **/
public interface ApiAccessService {
    /**
     * 是否允许访问api
     * @author nxq
     * @param ak:
     * @param sk:
     * @return boolean
     */
    boolean acceptAccess(String ak,String sk);
}
