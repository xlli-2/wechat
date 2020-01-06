package com.henu.wechat;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ApiTest {
    public static final String APP_ID = "18185355";
    public static final String API_KEY = "fOEf9PmfgVflGNC2oCGbG61o";
    public static final String SECRET_KEY = "DvSrsqeoR1jlLdqk5bqy6QeWEDfG9K7G";

    public static void main(String[] args) {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
//        client.setConnectionTimeoutInMillis(2000);
//        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//        client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//        client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
//        System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Li\\Desktop\\xxx.png";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }
    @Test
    public void fun1() {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 调用接口
        String url = "http://mmbiz.qpic.cn/mmbiz_jpg/SLMl3FWosxYm9ia8464iabe9Z54uydsChvibos04b0b1iaDESxWC7FmZmibfzgm8DlibycSdZibhSHZuxnFSxPlKyd0EA/0";
        JSONObject res = client.basicGeneral(url, new HashMap<String, String>());
        System.out.println(res.toString(2));

    }
}
