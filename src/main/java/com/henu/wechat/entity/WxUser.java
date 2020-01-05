package com.henu.wechat.entity;

import java.util.Date;
import lombok.Data;

@Data
public class WxUser {
    private Integer id;

    /**
     * 用户openid，当前公众号用户唯一标识
     */
    private String openid;

    /**
     * 关注状态，0取消，1订阅
     */
    private Integer subscribe;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别，1是男，2是女，0是未知
     */
    private Integer sex;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 语言
     */
    private String language;

    /**
     * 头像地址
     */
    private String headimgurl;

    /**
     * 关注时间
     */
    private Date subscribetime;

    /**
     * 多个公众号用户唯一标识
     */
    private String unionid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 分组
     */
    private String groupid;
}