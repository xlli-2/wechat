package com.henu.wechat.bean;

import com.henu.wechat.common.MessageUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TextMessageTest {
    @Test
    public void fun1() {
        Map<String, String> map = new HashMap<>();
        map.put("ToUserName", "1sda23");
        map.put("FromUserName", "321");
        map.put("CreateTime", System.currentTimeMillis()/1000+"");
        TextMessage textMessage = new TextMessage(map, "text", "测试text");
        String s = MessageUtil.messageToXml(textMessage);
        System.out.println(s);
    }

    @Test
    public void fun2() {
        Map<String, String> map = new HashMap<>();
        map.put("ToUserName", "1sda23");
        map.put("FromUserName", "321");
        map.put("CreateTime", System.currentTimeMillis()/1000+"");
        List<Article> list = new ArrayList<>();
        Article article = new Article("1", "1", "1", "1");
        list.add(article);
        NewsMessage newsMessage = new NewsMessage(map, "news", list);
        String s = MessageUtil.messageToXml(newsMessage);
        System.out.println(s);
    }

    @Test
    public void fun3() {
        Map<String, String> map = new HashMap<>();
        map.put("ToUserName", "1sda23");
        map.put("FromUserName", "321");
        map.put("CreateTime", System.currentTimeMillis()/1000+"");
        List<Article> list = new ArrayList<>();
        Article article = new Article("1", "1", "1", "1");
        list.add(article);
        BaseMessage newsMessage = new NewsMessage(map, "news", list);
        String s = MessageUtil.messageToXml(newsMessage);
        System.out.println(s);
    }
}