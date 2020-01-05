package com.henu.wechat.bean;

import com.henu.wechat.common.HttpClientUtil;
import org.json.JSONObject;

import java.io.IOException;

public class WechatFinalValue {
    //测试环境
    public static String APP_ID = "wx8f0b5b2fcc7a3264";

    //测试环境
    public static String APP_SECRET = "884c6399d9d41cdaae1c1e5b4d510334";

    // 接口调用请求说明 https请求方式: GET URL
    public static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token" +
            "?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;

    //接口调用请求说明 http请求方式：POST（请使用https协议） 需要access_token
    public static String MENU_CREAT_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    //access_token
    public static String ACCESS_TOKEN = "";

    //上一次获取时间
    public static Long ACCESS_TOKEN_TIME = 0L;

    //access_token 每次间隔刷新时间
    public static Long ACCESS_TOKEN_REFRESH_TIME = 6900L;


    public static Long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }


    /**
     * 获取access_token
     * 当前可能对数据库压力大
     *
     * @return
     * @throws IOException
     */
    public static String getAccessToken() {

        if (getCurrentTime() - ACCESS_TOKEN_TIME <= ACCESS_TOKEN_REFRESH_TIME) return ACCESS_TOKEN;

        try {
            String s = HttpClientUtil.doGet(ACCESS_TOKEN_URL);
            JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.has("access_token")) {
                ACCESS_TOKEN = (String) jsonObject.get("access_token");
                ACCESS_TOKEN_TIME = getCurrentTime() / 1000;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ACCESS_TOKEN;
    }
}
