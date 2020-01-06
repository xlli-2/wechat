package com.henu.wechat;

import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.common.HttpClientUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TemplateTest {

    @Test
    public void settTemplate() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=" + WechatFinalValue.getAccessToken();
        Map<String, String> mp = new HashMap<>();
        mp.put("industry_id1", "1");
        mp.put("industry_id2", "4");
//        JSONObject.JSONObject
        JSONObject jsonObject = new JSONObject(mp);
        try {
            String s = HttpClientUtil.doPost(url, jsonObject.toString());
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findTemplate() {
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=" + WechatFinalValue.getAccessToken();
        try {
            String s = HttpClientUtil.doGet(url);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sendTemplate() {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + WechatFinalValue.getAccessToken();

        String data = " {\n" +
                "           \"touser\":\"ogCiUjtouc4voxnRHfxigqYVej30\",\n" +
                "           \"template_id\":\"TUENe394R6GpadARkSxE1dxzj2emhZkX-wY4vjH2iPc\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"恭喜你投递成功！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"company\":{\n" +
                "                       \"value\":\"leetcode\",\n" +
                "                       \"color\":\"#213f11\"\n" +
                "                   },\n" +
                "                   \"time\": {\n" +
                "                       \"value\":\"2020年1月6号\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"result\": {\n" +
                "                       \"value\":\"hhhhhhhhhhhhhhhhhhhhhhhhhhh\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"祝您马到成功！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";

        try {
            String s = HttpClientUtil.doPost(url, data);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
