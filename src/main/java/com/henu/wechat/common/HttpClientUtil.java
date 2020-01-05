package com.henu.wechat.common;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUtil {
    public static String doGet(String url) throws IOException {
        //指定Http请求格式
        HttpGet httpGet = new HttpGet(url);
        //创建Http连接
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //执行发送请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        //判断请求是否成功
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String str = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            return str;
        }
        return null;
    }
    public static String doPost(String url, String data) throws IOException {
        //指定Http请求格式
        HttpPost httpPost = new HttpPost(url);
        //创建Http连接
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //数据放入到post请求之中
        httpPost.setEntity(new StringEntity(data, "utf-8"));
        //执行发送请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        //判断请求是否成功
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String str = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            return str;
        }
        return null;
    }


}
