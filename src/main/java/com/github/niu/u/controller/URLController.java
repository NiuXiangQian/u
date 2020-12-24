package com.github.niu.u.controller;

import com.github.niu.u.common.core.R;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.model.vo.ShortUrlVo;
import com.github.niu.u.service.URLService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: url控制器
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/23 8:48 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/23 8:48 下午
 * @updateRemark:
 * @version: 1.0
 **/
@RestController
@RequestMapping("url")
public class URLController {

    @Autowired
    private URLService urlService;

    @RequestMapping("/generate")
    public R<Object> generate(String url,Integer validTime) throws BaseException {
        if (StringUtils.isEmpty(url)){
            return R.failed("url不能为空");
        }
        ShortUrlVo shortURL = urlService.generate(url,
                60L * 30L  //默认有效时间 30分钟
        );

        return R.ok(shortURL);
    }
}
