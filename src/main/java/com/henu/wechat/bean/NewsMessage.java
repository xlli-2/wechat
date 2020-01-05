package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage{
    private String ArticleCount;
    private List<Article> Articles;


    public NewsMessage(Map<String, String> requestMap, String msgType, List<Article> articles) {
        super(requestMap, msgType);
        ArticleCount = articles.size()+"";
        Articles = articles;
    }
}
