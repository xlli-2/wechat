package com.henu.wechat.entity;

import lombok.Data;

@Data
public class Qrcode {
    private Integer id;

    /**
    * openid
    */
    private String openid;

    /**
    * 场景值，用户ID字符串
    */
    private String sceneStr;

    /**
    * 有效时间
    */
    private Integer expireSeconds;

    /**
    * 二维码ticket
    */
    private String ticket;

    /**
    * 创建时间
    */
    private Integer createTime;
}