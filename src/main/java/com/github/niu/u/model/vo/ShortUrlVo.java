package com.github.niu.u.model.vo;

/**
 * @description: 短网址vo
 * @author: nxq email: niuxiangqian163@163.com
 * @createDate: 2020/12/24 4:40 下午
 * @updateUser: nxq email: niuxiangqian163@163.com
 * @updateDate: 2020/12/24 4:40 下午
 * @updateRemark:
 * @version: 1.0
 **/
public class ShortUrlVo {
    private String orgUrl; //原url
    private String shortUrl; //短网址url
    private String shortTarget; //短网址标记
    private Long   validTime;//有效时间

    public String getOrgUrl() {
        return orgUrl;
    }

    public ShortUrlVo setOrgUrl(String orgUrl) {
        this.orgUrl = orgUrl;
        return this;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public ShortUrlVo setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
        return this;
    }

    public String getShortTarget() {
        return shortTarget;
    }

    public ShortUrlVo setShortTarget(String shortTarget) {
        this.shortTarget = shortTarget;
        return this;
    }

    public Long getValidTime() {
        return validTime;
    }

    public ShortUrlVo setValidTime(Long validTime) {
        this.validTime = validTime;
        return this;
    }
}
