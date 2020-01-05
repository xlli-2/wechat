package com.henu.wechat.bean;

import com.henu.wechat.common.HttpClientUtil;
import org.json.JSONObject;

import java.io.IOException;

public class AccessToken {
    /**
     * @return access_token
     */
    public static String getAccessToken() {
        String access_token = null;
        // 需要请求的地址
        String url = WechatFinalValue.ACCESS_TOKEN_URL;
        try {
            String s = HttpClientUtil.doGet(url);
            if (s != null) {
                JSONObject jsonObject = new JSONObject(s);
                //TODO 如何处理没有指定的key
                access_token = (String) jsonObject.get("access_token");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return access_token;
    }
}
