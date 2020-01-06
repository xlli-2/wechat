package com.henu.wechat.bean;

import lombok.Data;

@Data
public class ClickButton extends AbstractButon {
    private String type = "click";
    private String key;


    public ClickButton(String name, String key) {
        super(name);
        this.key = key;
    }
}
