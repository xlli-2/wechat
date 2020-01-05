package com.henu.wechat.service;

import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.common.HttpClientUtil;
import com.henu.wechat.common.JsonUtil;
import com.henu.wechat.dao.WxUserMapper;
import com.henu.wechat.entity.WxUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class WxUserService {

    @Resource
    private WxUserMapper wxUserMapper;

    private WxUser getWxUser(String openid) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/" +
                "info?access_token=" + WechatFinalValue.getAccessToken() + "&openid=" + openid + "&lang=zh_CN";
        try {
            String s = HttpClientUtil.doGet(url);
            WxUser wxUser = JsonUtil.fromJson(s, WxUser.class);
            return wxUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void msgType(Map<String, String> elementMap) {
        String openid = elementMap.get("FromUserName");
        String msgType = elementMap.get("MsgType");
        WxUser wxUserDB = wxUserMapper.selectByOpenid(openid);
        if (msgType.equals("event")) {
            WxUser wxUser = getWxUser(openid);
            if (elementMap.get("Event").equals("subscribe") && wxUserDB == null) {
                wxUserMapper.insert(wxUser);
            }
            else wxUserMapper.updateByOpenid(wxUser);
        } else if(msgType.equals("text")) {

        }
    }

//    private String selectXmlData() {
//
//    }


    public String updateWxUser(Map<String, String> elementMap) {
//        String toUserName = elementMap.get("ToUserName");
//        String fromUserName = elementMap.get("FromUserName");
//        String createTime = elementMap.get("CreateTime");
//        String msgType = elementMap.get("MsgType");
        msgType(elementMap);
        return "";
    }
}
