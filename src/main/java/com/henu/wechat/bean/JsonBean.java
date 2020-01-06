package com.henu.wechat.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JsonBean {
    // 0 success -1 fail 9999 异常
    private int code;
    private String msg;
    private Object data;
}
