package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class TextMessage extends BaseMessage {
    private String Content;


    public TextMessage(Map<String, String> requestMap, String msgType, String content) {
        super(requestMap, msgType);
        Content = content;
    }
}
