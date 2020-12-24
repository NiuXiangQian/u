package com.github.niu.u.service;

import com.github.niu.u.common.exception.BaseException;

/**
 * @description: url的服务
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/23 8:52 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/23 8:52 下午
 * @updateRemark:
 * @version: 1.0
 **/
public interface URLService {

    /**
     * 生成短网址
     * @author nxq
     * @param srcUrl:  原来的url
     * @param valid: 有效时间 -1为永久有效
     * @return java.lang.String
     */
    String generate(String srcUrl,Long valid) throws BaseException;
}
