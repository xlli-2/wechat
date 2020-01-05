package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;


@Data
@XStreamAlias("xml")
public class BaseMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    public BaseMessage(Map<String, String> requestMap, String msgType) {
        ToUserName = requestMap.get("FromUserName");
        FromUserName = requestMap.get("ToUserName");
        CreateTime = System.currentTimeMillis()/1000+"";
        MsgType = msgType;
    }
}
