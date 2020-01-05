package com.henu.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.Map;

@Data
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage {
    @XStreamAlias("Music")
    private Music music;

    public MusicMessage(Map<String, String> requestMap, String msgType, Music music) {
        super(requestMap, msgType);
        this.music = music;
    }
}
