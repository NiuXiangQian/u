package com.github.niu.u.service;

import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.model.vo.ShortUrlVo;

/**
 * url的服务
 *
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 8:52 下午
 **/
public interface URLService {

    /**
     * 生成短网址
     *
     * @param srcUrl :  原来的url
     * @param valid  : 有效时间 -1为永久有效
     * @return java.lang.String
     * @author nxq
     */
    ShortUrlVo generate(String srcUrl, Long valid) throws BaseException;

    /**
     * 根据短网址target标记解析原网址
     *
     * @param shortTarget: 短网址的标记
     * @return com.github.niu.u.model.vo.ShortUrlVo
     * @author nxq
     */
    ShortUrlVo restoreByTarget(String shortTarget) throws BaseException;

    /**
     * 根据短网址target标记解析原网址
     *
     * @param shortUrl: 完整的短网址
     * @return com.github.niu.u.model.vo.ShortUrlVo
     * @author nxq
     */
    ShortUrlVo restoreByShortUrl(String shortUrl) throws BaseException;
}
