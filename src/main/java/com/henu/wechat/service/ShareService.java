package com.henu.wechat.service;

import com.henu.wechat.bean.JsonBean;
import com.henu.wechat.bean.MyTeam;
import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.dao.PullUserMapper;
import com.henu.wechat.dao.QrcodeMapper;
import com.henu.wechat.dao.WxUserMapper;
import com.henu.wechat.entity.Qrcode;
import com.henu.wechat.entity.WxUser;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ShareService {

    @Resource
    QrcodeMapper qrcodeMapper;

    @Resource
    WxUserMapper wxUserMapper;

    @Resource
    PullUserMapper pullUserMapper;
    /**
     * 网页授权 和 获取二维码
     * @param code
     * @return
     */
    public JsonBean insertOauthAndQrCode(String code) {
        String s = WechatFinalValue.getAccessTokenAndOpenid(code);
        Map<String, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject(s);
        if(jsonObject.has("openid")) {
            map.put("openid", jsonObject.getString("openid"));

            System.out.println(jsonObject);
            WxUser wxUser = wxUserMapper.selectByOpenid(jsonObject.getString("openid"));
            Qrcode qrcode = (Qrcode) insertQrCode(wxUser).getData();
            map.put("ticket", qrcode.getTicket());
            map.put("wxUser", wxUser);
        }
        return new JsonBean(0, "", map);
    }

    public JsonBean insertQrCode(WxUser wxUser) {
        //根据 openid 获取用户是否生成过二维码
        Qrcode qrcode = qrcodeMapper.selectOneByOpenid(wxUser.getOpenid());
        if (qrcode == null) {
            qrcode = WechatFinalValue.getQrcode(wxUser);
            qrcodeMapper.insert(qrcode);
        } else {
            // 没生成二维码 生成二维码
            if (WechatFinalValue.getCurrentTime() - qrcode.getCreateTime() > qrcode.getExpireSeconds() - 100) {
                qrcode = WechatFinalValue.getQrcode(wxUser);
                qrcodeMapper.updateByPrimaryKeySelective(qrcode);
            }
        }
        return new JsonBean(-1, "", qrcode);
    }

    public Map<String, Object> selectMyTeam(String openid){
        List<MyTeam> myTeams = pullUserMapper.selectMyTeamByOpenid(openid);
        Map<String, Object> map = new HashMap<>();
        map.put("list", myTeams);
        return map;
    }

}
