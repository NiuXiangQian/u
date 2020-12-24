package com.github.niu.u.controller;

import com.github.niu.u.common.core.R;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.model.ApiGenerateReq;
import com.github.niu.u.service.ApiAccessService;
import com.github.niu.u.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description: api接口
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/24 11:48 上午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/24 11:48 上午
 * @updateRemark:
 * @version: 1.0
 **/
@RestController
@RequestMapping("/api")
public class ApiURLController {
    @Autowired
    private ApiAccessService apiAccessService;
    @Autowired
    private URLService urlService;

    @PostMapping("/v1/generate")
    public R<String> generate(@Valid @RequestBody ApiGenerateReq apiGenerateReq) throws BaseException {
       boolean acc =  apiAccessService.acceptAccess(apiGenerateReq.getAk(),apiGenerateReq.getSk());
       if (acc){
          String shortURL = urlService.generate(apiGenerateReq.getUrl(),apiGenerateReq.getValid());
          return R.ok(shortURL);
       }

        return R.failed("对不起，您没有权限访问，请检查ak 和 sk");
    }

}
