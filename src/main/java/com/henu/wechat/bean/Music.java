package com.henu.wechat.bean;

import lombok.Data;

@Data
public class Music {
    private String Title;
    private String Description;
    private String MusicURL;
    private String HQMusicUrl;
    private String ThumbMediaId;

    public Music(String title, String description, String musicURL, String HQMusicUrl, String thumbMediaId) {
        Title = title;
        Description = description;
        MusicURL = musicURL;
        this.HQMusicUrl = HQMusicUrl;
        ThumbMediaId = thumbMediaId;
    }
}
