package com.github.niu.u.common.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @description: md5工具类
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/23 9:00 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/23 9:00 下午
 * @updateRemark:
 * @version: 1.0
 **/
public class MD5Util {
    public static String encryptStr(String str){
        return  DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8)).substring(8,24);

    }
}
