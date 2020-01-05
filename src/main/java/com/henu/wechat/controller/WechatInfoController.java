package com.henu.wechat.controller;

import com.henu.wechat.common.CommonUtil;
import com.henu.wechat.service.CoreService;
import com.henu.wechat.service.WxUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class WechatInfoController {

    @Resource
    private WxUserService wxUserService;

    @Resource
    private CoreService coreService;

//    @RequestMapping("index")
//    private String index(HttpServletRequest request) {
//        // 平台中写死的token
//        String token = "20200102";
//        // 字典排序
//        List<String> list = new ArrayList<String>();
//        list.add(token);
//        list.add(request.getParameter("nonce"));
//        list.add(String.valueOf(request.getParameter("timestamp")));
//        String str = CommonUtil.getListSort(list);
//        //sha1加密
//        String sha1 = CommonUtil.SHA1(str);
//        //对比微信发送的签名与接受的随机数，时间戳和token加密后如果对比成功返回随机字符串
//        if(request.getParameter("signature").equals(sha1)) {
//            return request.getParameter("echostr");
//        }
//        return "";
//    }

//    @RequestMapping("index")
//    public String doPost(HttpServletRequest request) {
//        String xml = "";
//        try {
//            ServletInputStream inputStream = request.getInputStream();
//            String respDataToString = CommonUtil.getRespDataToString(inputStream);
//            Map<String, String> elementMap = CommonUtil.xmlToMap(respDataToString);
//            wxUserService.updateWxUser(elementMap);
//            xml = coreService.processRequest(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return xml;
//    }

    @RequestMapping("index")
    public String doPost(HttpServletRequest request) {
        String xml = coreService.processRequest(request);
        System.out.println(xml);
        return xml;
    }
}
