package com.henu.wechat.service;

import com.henu.wechat.bean.JsonBean;
import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.common.HttpClientUtil;
import com.henu.wechat.common.JsonUtil;
import com.henu.wechat.dao.WxUserMapper;
import com.henu.wechat.entity.WxUser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class WxUserService {

    @Resource
    private WxUserMapper wxUserMapper;



    private void msgType(Map<String, String> elementMap) {
        String openid = elementMap.get("FromUserName");
        String msgType = elementMap.get("MsgType");
        WxUser wxUserDB = wxUserMapper.selectByOpenid(openid);
        if (msgType.equals("event")) {
            WxUser wxUser = WechatFinalValue.getWxUser(openid);
            if (elementMap.get("Event").equals("subscribe") && wxUserDB == null) {
                wxUserMapper.insert(wxUser);
            }
            else wxUserMapper.updateByOpenid(wxUser);
        } else if(msgType.equals("text")) {

        }
    }



    public String updateWxUser(Map<String, String> elementMap) {
//        String toUserName = elementMap.get("ToUserName");
//        String fromUserName = elementMap.get("FromUserName");
//        String createTime = elementMap.get("CreateTime");
//        String msgType = elementMap.get("MsgType");
        msgType(elementMap);
        return "";
    }

    public JsonBean insertOauth(String code) {
        JsonBean JsonBean = new JsonBean(-1, "", "");
        String s = WechatFinalValue.getAccessTokenAndOpenid(code);
        JSONObject jsonObject = new JSONObject(s);
        if (jsonObject.has("access_token")) {
            String access_token = jsonObject.getString("access_token");
            String openid = jsonObject.getString("openid");
            WxUser wxUser = wxUserMapper.selectByOpenid(openid);
            if (wxUser != null) {
                JsonBean = new JsonBean(0, "", wxUser);
            } else {
                //TODO 判断一下授权方式
            }
        }
        return JsonBean;
    }

    public String getTicket() {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + WechatFinalValue.getAccessToken();
        String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"谢谢你关注我\"}}}\n";
        String ticket = "";
        try {
            ticket = HttpClientUtil.doPost(url, data);
            JSONObject jsonObject = new JSONObject(ticket);
            ticket = jsonObject.getString("ticket");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
