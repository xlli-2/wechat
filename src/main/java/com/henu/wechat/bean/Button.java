package com.henu.wechat.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Button {
    private List<AbstractButon> button = new ArrayList<>();
}
