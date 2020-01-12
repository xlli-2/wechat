package com.henu.wechat.controller;

import com.henu.wechat.bean.JsonBean;
import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.entity.Qrcode;
import com.henu.wechat.entity.WxUser;
import com.henu.wechat.service.QrcodeService;
import com.henu.wechat.service.ShareService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class ShareController {

    @Resource
    QrcodeService qrcodeService;
    @Resource
    ShareService shareService;


    @RequestMapping("getRrCode")
    public JsonBean getQrCode(HttpSession session, String code, String targetUrl) {
        WxUser wxUser = (WxUser) session.getAttribute("wxUser");
        JsonBean jsonBean = null;
        Map<String, Object> resultMap = WechatFinalValue.getPageJsPermission(targetUrl);
        if(wxUser == null) {
            jsonBean = shareService.insertOauthAndQrCode(code);
            Map<String, Object> map = (Map<String, Object>) jsonBean.getData();
            resultMap.putAll(map);
            session.setAttribute("wxUser", map.get("wxUser"));
            //TODO 如果对象为空 且 code 为-1 前端页面考虑重定向
        }
        else {
            //session 有信息直接取
            Qrcode qrcode = (Qrcode) qrcodeService.insertQrCode(wxUser).getData();
            resultMap.put("openid", qrcode.getOpenid());
            resultMap.put("ticket", qrcode.getTicket());
        }
        resultMap.putAll(shareService.selectMyTeam(resultMap.get("openid")+""));
        return new JsonBean(1, "", resultMap);
    }

}
