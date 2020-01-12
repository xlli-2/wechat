package com.henu.wechat;

import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.common.HttpClientUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class AccessTokenTest {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                + WechatFinalValue.getAccessToken();
        /**
         * 做数据
         */
        //最外成json
        JSONObject json = new JSONObject();
        //json数组，里面放的是三个一级菜单
        JSONArray jsonArray = new JSONArray();
        //第一个一级菜单
        JSONObject json1 = new JSONObject();
        json1.put("type", "view");
        json1.put("name", "首页1");
        String addr = "http://xy5cpqu.hn3.mofasuidao.cn/app/index.html";
        URLEncoder.encode(addr, "utf-8");
        String oauth2 = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid="+WechatFinalValue.APP_ID+"&redirect_uri="+addr+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        json1.put("url", oauth2);
        //第二个一级菜单
        JSONObject json2 = new JSONObject();
        json2.put("type", "view");
        json2.put("name", "购物车");
        json2.put("url", "http://xy5cpqu.hn3.mofasuidao.cn/app/shopcar.html");
        //第三个一级菜单
        JSONObject json3 = new JSONObject();
        json3.put("type", "view");
        json3.put("name", "个人中心");
        json3.put("url", "http://xy5cpqu.hn3.mofasuidao.cn/app/pcenter.html");
        //将3个一级菜单放入到json数组之中
        jsonArray.put(json1);
        jsonArray.put(json2);
        jsonArray.put(json3);
        json.put("button", jsonArray);
        try {
            String s = HttpClientUtil.doPost(url, json.toString());
            JSONObject jsonObject = new JSONObject(s);
            Integer errcode = (Integer) jsonObject.get("errcode");
            if (errcode.equals(0)) {
                System.out.println("自定义菜单生成完毕");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fun1() {
        System.out.println(new Date().getTime());
        System.out.println(System.currentTimeMillis());
    }
}
