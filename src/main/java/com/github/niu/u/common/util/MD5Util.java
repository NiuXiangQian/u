package com.github.niu.u.common.util;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 *  md5工具类
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 9:00 下午
 **/
public class MD5Util {
    public static String encryptStr(String str){
        return  DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8)).substring(8,17)+ RandomStringUtils.random(3);

    }
}
