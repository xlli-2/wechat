package com.henu.wechat.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class SubButton extends AbstractButon{
    private List<AbstractButon> sub_button = new ArrayList<>();

    public SubButton(String name) {
        super(name);
    }
}
