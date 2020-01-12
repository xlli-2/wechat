package com.henu.wechat.entity;

import lombok.Data;

@Data
public class PullUser {
    private Integer id;

    /**
    * 扫码者openid
    */
    private String scanId;

    /**
    * 分享者openid
    */
    private String shareId;
}