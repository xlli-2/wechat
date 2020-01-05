package com.henu.wechat.entity;

import lombok.Data;

@Data
public class User {
    private String userName;
    private String passwd;

    public User(String userName, String passwd) {
        this.userName = userName;
        this.passwd = passwd;
    }
}
