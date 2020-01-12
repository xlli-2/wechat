package com.henu.wechat.service;

import com.henu.wechat.bean.JsonBean;
import com.henu.wechat.bean.WechatFinalValue;
import com.henu.wechat.dao.QrcodeMapper;
import com.henu.wechat.entity.Qrcode;
import com.henu.wechat.entity.WxUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QrcodeService {

    @Resource
    private QrcodeMapper qrcodeMapper;

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




}
