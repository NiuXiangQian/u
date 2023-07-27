package com.github.niu.u.controller;

import com.github.niu.u.common.core.R;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.model.vo.ShortUrlVo;
import com.github.niu.u.service.URLService;
import org.apache.commons.lang.StringUtils;
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

    @Resource
    private URLService urlService;

    @RequestMapping("/generate")
    public R<Object> generate(String url, Long validTime){
        if (StringUtils.isNotBlank(url)) {
            return R.failed("url不能为空");
        }

        return R.ok(urlService.generate(url, validTime));
    }
}
