package com.github.niu.u.model.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * api请求的vo
 *
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 11:56 上午
 **/
public class ApiGenerateReq {


    @NotBlank(message = "url不能为空")
    private String url;

    private Long valid;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getValid() {
        return valid;
    }

    public void setValid(Long valid) {
        this.valid = valid;
    }
}
