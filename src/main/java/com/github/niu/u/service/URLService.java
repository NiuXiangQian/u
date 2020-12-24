package com.github.niu.u.service;

import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.model.vo.ShortUrlVo;

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
     * @param srcUrl :  原来的url
     * @param valid : 有效时间 -1为永久有效
     * @return java.lang.String
     */
    ShortUrlVo generate(String srcUrl, Long valid) throws BaseException;

    /**
     * 根据短网址target标记解析原网址
     * @author nxq
     * @param shortTarget: 短网址的标记
     * @return com.github.niu.u.model.vo.ShortUrlVo
     */
    ShortUrlVo restoreByTarget(String shortTarget) throws BaseException;

    /**
     * 根据短网址target标记解析原网址
     * @author nxq
     * @param shortUrl:  完整的短网址
     * @return com.github.niu.u.model.vo.ShortUrlVo
     */
    ShortUrlVo restoreByShortUrl(String shortUrl) throws BaseException;
}
