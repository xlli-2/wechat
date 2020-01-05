package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class VideoMessage extends BaseMessage {
    private String MediaId;
    private String Title;
    private String Description;

    public VideoMessage(Map<String, String> requestMap, String msgType, String mediaId, String title, String description) {
        super(requestMap, msgType);
        MediaId = mediaId;
        Title = title;
        Description = description;
    }
}
