package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("item")
public class Article {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public Article(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }
}
