package com.henu.wechat.bean;

import com.henu.wechat.common.HttpClientUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ButtonTest {

    @Test
    public void testButton() {
        //菜单对象
        Button btn = new Button();
        //第一个 一级菜单
        btn.getButton().add(new ClickButton("一级点击", "1"));
        //第二个 一级菜单
        btn.getButton().add(new ViewButton("一级跳转", WechatFinalValue.getOnlineAuto("http://xy5cpqu.hn3.mofasuidao.cn/app/index.html")));
        //第三个 一级菜单 （子菜单增加三个二级菜单）
        SubButton sb = new SubButton("有子菜单");

        sb.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
        sb.getSub_button().add(new ClickButton("点击", "32"));
        sb.getSub_button().add(new ViewButton("百度一下", "http://baidu.com"));


        btn.getButton().add(sb);
        JSONObject jsonObject = new JSONObject(btn);
//        System.out.println(jsonObject.toString());
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+WechatFinalValue.getAccessToken()+"";
        try {
            String s = HttpClientUtil.doPost(url, jsonObject.toString());
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
