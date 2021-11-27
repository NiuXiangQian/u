package com.github.niu.u.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 1:21 下午
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "/web/index.html";
    }
}
