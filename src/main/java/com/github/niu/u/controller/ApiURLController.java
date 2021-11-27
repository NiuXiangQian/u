package com.github.niu.u.controller;

import com.github.niu.u.common.core.R;
import com.github.niu.u.common.exception.BaseException;
import com.github.niu.u.model.req.ApiGenerateReq;
import com.github.niu.u.model.vo.ShortUrlVo;
import com.github.niu.u.service.ApiAccessService;
import com.github.niu.u.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *  api接口
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 11:48 上午
 **/
@RestController
@RequestMapping("/api/v1")
public class ApiURLController {
    @Autowired
    private ApiAccessService apiAccessService;
    @Autowired
    private URLService urlService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 生成一个短链接
     * @author nxq
     * @param apiGenerateReq:
     * @return com.github.niu.u.common.core.R<java.lang.String>
     */
    @PostMapping("/generate")
    public R<ShortUrlVo> generate(@Valid @RequestBody ApiGenerateReq apiGenerateReq) throws BaseException {
          ShortUrlVo shortURL = urlService.generate(apiGenerateReq.getUrl(),apiGenerateReq.getValid());
          return R.ok(shortURL);
    }
    /**
     * 根据短链接的target解析原地址
     * @author nxq
     * @param target:
     * @return com.github.niu.u.common.core.R<com.github.niu.u.model.vo.ShortUrlVo>
     */
    @GetMapping("/restore/{target}")
    public R<ShortUrlVo> restore(@PathVariable String target) throws BaseException {

        return R.ok(urlService.restoreByTarget(target));
    }
    /**
     * 根据完整的短链接解析原地址
     * @author nxq
     * @param url:
     * @return com.github.niu.u.common.core.R<com.github.niu.u.model.vo.ShortUrlVo>
     */
    @GetMapping("/restoreFullUrl/{url}")
    public R<ShortUrlVo> restoreFullUrl(@PathVariable String url) throws BaseException {

        return R.ok(urlService.restoreByTarget(url));
    }

}
