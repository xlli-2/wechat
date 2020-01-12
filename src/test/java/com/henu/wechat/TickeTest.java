package com.henu.wechat;

import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.common.HttpClientUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TickeTest {
    @Test
    public void fun1() {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + WechatFinalValue.getAccessToken();
        String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"谢谢你关注我\"}}}\n";
        try {
            String s = HttpClientUtil.doPost(url, data);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun2() {
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQE88DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyQ2NwWTlSMEVha2UxMXhqdHh1Y1gAAgTh2BNeAwSAOgkA";
    }

    @Test
    public void fun3() {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" +
                "access_token="+WechatFinalValue.getAccessToken()+"&type=jsapi";

        try {
            String s = HttpClientUtil.doGet(url);
            JSONObject jsonObject = new JSONObject(s);
            System.out.println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
