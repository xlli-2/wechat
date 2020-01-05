package com.henu.wechat.service;

import com.henu.wechat.common.CommonUtil;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ChatService {
    //1.问答
    public static final String APPKEY ="b8b54cb262deb51e7e5b19145dd470b4";
    public static String Chat(String info){
        String result =null;
        String url ="http://op.juhe.cn/iRobot/index";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请到的本接口专用的APPKEY
        params.put("info",info);//要发送给机器人的内容，不要超过30个字符
//        params.put("dtype","");//返回的数据的格式，json或xml，默认为json
//        params.put("loc","");//地点，如北京中关村
//        params.put("lon","");//经度，东经116.234632（小数点后保留6位），需要写为116234632
//        params.put("lat","");//纬度，北纬40.234632（小数点后保留6位），需要写为40234632
//        params.put("userid","");//1~32位，此userid针对您自己的每一个用户，用于上下文的关联

        try {
            result = CommonUtil.net(url, params, "GET");
            JSONObject object = new JSONObject(result);
            if(object.getInt("error_code")==0){
                return object.getJSONObject("result").get("text") + "";
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
                return object.get("error_code")+":"+object.get("reason");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "内部错误";
    }
}
