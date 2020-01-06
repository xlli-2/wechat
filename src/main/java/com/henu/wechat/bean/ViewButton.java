package com.henu.wechat.bean;

import lombok.Data;

@Data
public class ViewButton extends AbstractButon {
    private String type = "view";
    private String url;

    public ViewButton(String name, String url) {
        super(name);
        this.url = url;
    }
}
