package com.github.niu.u.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/24 1:21 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/24 1:21 下午
 * @updateRemark:
 * @version: 1.0
 **/
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "/web/index.html";
    }
}
