package com.henu.wechat.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhotoOrAlbumButton extends AbstractButon {
    private String type="pic_photo_or_album";
    private String key;
    private List<AbstractButon> sub_button = new ArrayList<>();

    public PhotoOrAlbumButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
