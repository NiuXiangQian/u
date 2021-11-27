package com.github.niu.u.model.req;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author niuxiangqian
 * @version 1.0
 * @since 2020/12/24 3:34 下午
 **/
public class AkSkReq {
    @NotBlank(message = "ak不能为空")
    private String ak;
    @NotBlank(message = "sk不能为空")
    private String sk;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk;
    }
}
