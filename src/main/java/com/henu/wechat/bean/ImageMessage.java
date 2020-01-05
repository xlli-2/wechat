package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class ImageMessage extends BaseMessage {
    private String MediaId;

    public ImageMessage(Map<String, String> requestMap, String msgType, String mediaId) {
        super(requestMap, msgType);
        MediaId = mediaId;
    }
}
