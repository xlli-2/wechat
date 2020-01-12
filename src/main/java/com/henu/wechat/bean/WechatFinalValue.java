package com.henu.wechat.bean;

import com.henu.wechat.common.CommonUtil;
import com.henu.wechat.common.DateUtil;
import com.henu.wechat.common.HttpClientUtil;
import com.henu.wechat.common.JsonUtil;
import com.henu.wechat.entity.Qrcode;
import com.henu.wechat.entity.WxUser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class WechatFinalValue {
    //测试环境
    public static String APP_ID = "wx8f0b5b2fcc7a3264";

    //测试环境
    public static String APP_SECRET = "884c6399d9d41cdaae1c1e5b4d510334";

    //ACCESS_TOKEN
    public static String ACCESS_TOKEN = "";

    //ACCESS_TOKEN上一次获取时间
    public static Long ACCESS_TOKEN_TIME = 0L;

    //JSAPI_TICKET
    public static String JSAPI_TICKET = "";

    //JSAPI_TICKET上一次获取时间
    public static Long JSAPI_TICKET_TIME = 0L;

    public static Long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }


    public static void sendTemplateMessage(String opnid, String share_name, String pull_name) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + getAccessToken();
        String data =" {\n" +
                "           \"touser\":\"ogCiUjtouc4voxnRHfxigqYVej30\",\n" +
                "           \"template_id\":\"UUY5-S01fsz6X6qAHDUobn551JLf_9GONy-hrZQaMV4\",         \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"恭喜 "+share_name+" 又成功拉取一个用户\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"拉取的用户昵称是 "+pull_name+"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\""+ DateUtil.getTime() +"\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"再接再厉，马上满级！\",\n" +
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

    /**
     * 获取JsapiTicket微信js调用临时票据
     * @return
     */
    public static String getJsapiTicket() {
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?" +
                "access_token=" + getAccessToken() + "&type=jsapi";
        if(getCurrentTime() - JSAPI_TICKET_TIME <= 6900L) return JSAPI_TICKET;
        try {
            String s = HttpClientUtil.doGet(url);
            JSONObject jsonObject = new JSONObject(s);
            JSAPI_TICKET = jsonObject.getString("ticket");
            JSAPI_TICKET_TIME = getCurrentTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSAPI_TICKET;
    }

    /**
     * JS调用接口权限注入加密
     * @param targetUrl
     * @return
     */
    public static Map<String, Object> getPageJsPermission(String targetUrl) {
        String noncestr = CommonUtil.generateNonceStr();
        Long currentTime = getCurrentTime();
        String url = "jsapi_ticket=" + getJsapiTicket() +
                "&noncestr=" + noncestr +
                "&timestamp=" + currentTime+
                "&url=" + targetUrl;

        String signature = CommonUtil.SHA1(url);
        System.out.println(signature);
        Map<String, Object> map = new HashMap<>();
        map.put("appId",APP_ID);
        map.put("timestamp",currentTime);
        map.put("nonceStr",noncestr);
        map.put("signature",signature);
        System.out.println(map.toString());
        return map;
    }

    /**
     * 封装地址（加上手动授权
     *
     * @param addr 跳转地址
     * @return
     */
    public static String getOnlineAuto(String addr) {
        String url = "";
        try {
            URLEncoder.encode(addr, "utf-8");
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                    "appid=" + APP_ID +
                    "&redirect_uri=" + addr +
                    "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 获取access_token
     * 当前可能对数据库压力大
     *
     * @return
     * @throws IOException
     */
    public static String getAccessToken() {
        if (getCurrentTime() - ACCESS_TOKEN_TIME <= 6900L) return ACCESS_TOKEN;

        String url = "https://api.weixin.qq.com/cgi-bin/token" +
                "?grant_type=client_credential&appid=" + APP_ID + "&secret=" + APP_SECRET;
        try {
            String s = HttpClientUtil.doGet(url);
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

    /**
     * 通过code 获取access_token 和 openid
     * 网页授权
     *
     * @param code
     * @return
     */
    public static String getAccessTokenAndOpenid(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" + APP_ID + "&secret=" + APP_SECRET +
                "&code=" + code + "&grant_type=authorization_code";
        String s = null;
        try {
            s = HttpClientUtil.doGet(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


    /**
     * 获取二维码
     *
     * @param wxUser
     * @return
     */
    public static Qrcode getQrcode(WxUser wxUser) {
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?" +
                "access_token=" + getAccessToken();
        String data = "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": " +
                "{\"scene_str\": \"" + wxUser.getOpenid() + "\"}}}\n";
        return getQrcode(wxUser, url, data);
    }

    public static Qrcode getQrcode(WxUser wxUser, String url, String data) {
        Qrcode qrcode = new Qrcode();
        try {
            String s = HttpClientUtil.doPost(url, data);
            JSONObject jsonObject = new JSONObject(s);
            System.out.println(jsonObject);
            qrcode.setOpenid(wxUser.getOpenid());
            qrcode.setCreateTime(Integer.valueOf(WechatFinalValue.getCurrentTime() + ""));
            qrcode.setTicket(jsonObject.getString("ticket"));
            qrcode.setExpireSeconds(Integer.valueOf(jsonObject.get("expire_seconds") + ""));
            qrcode.setId(wxUser.getId());
            qrcode.setSceneStr(wxUser.getOpenid());
            System.out.println(qrcode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return qrcode;
    }

    public static WxUser getWxUser(String openid) {
        String url = "https://api.weixin.qq.com/cgi-bin/user/" +
                "info?access_token=" + getAccessToken() + "&openid=" + openid + "&lang=zh_CN";
        try {
            String s = HttpClientUtil.doGet(url);
            WxUser wxUser = JsonUtil.fromJson(s, WxUser.class);
            return wxUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
