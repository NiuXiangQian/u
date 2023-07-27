package com.github.niu.u.controller;

import com.github.niu.u.common.core.R;
import com.github.niu.u.config.MvcExceptionHandler;
import com.github.niu.u.service.URLService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * url控制器
 *
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/23 8:48 下午
 **/
@RestController
@RequestMapping("url")
public class URLController {
    private final static Logger logger = LoggerFactory.getLogger(URLController.class);


    @Resource
    private URLService urlService;

    @RequestMapping("/generate")
    public R<Object> generate(String url, Long validTime) {
        logger.info("url:{},validTime:{}", url, validTime);
        if (StringUtils.isNotBlank(url)) {
            return R.failed("url不能为空");
        }

        return R.ok(urlService.generate(url, validTime));
    }
}
