package com.henu.wechat.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Message {
    private Integer id;

    /**
    * 推送类型
    */
    private String type;

    /**
    * 状态，0禁用；1启用
    */
    private String status;

    /**
    * 标题
    */
    private String title;

    /**
    * 描述
    */
    private String description;

    /**
    * 图片地址
    */
    private String picurl;

    /**
    * 请求地址
    */
    private String url;

    /**
    * 包含关键字
    */
    private String keyword;

    /**
    * 创建日期
    */
    private Date createtime;

    /**
    * 排序
    */
    private Integer sort;
}