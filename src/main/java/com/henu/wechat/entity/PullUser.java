package com.henu.wechat.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
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

    public PullUser(String scanId, String shareId) {
        this.scanId = scanId;
        this.shareId = shareId;
    }
}