package com.henu.wechat.service;

import com.henu.wechat.bean.*;
import com.henu.wechat.common.MessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CoreService {

//    @Resource
//    BaseMessage baseMessage;
//    @Resource
//    ImageMessage imageMessage;
//    @Resource
//    MusicMessage musicMessage;
//    @Resource
//    NewsMessage newsMessage;
//    @Resource
//    TextMessage textMessage;
//    @Resource
//    VideoMessage videoMessage;
//    @Resource
//    VoiceMessage voiceMessage;
//    @Resource
//    Music music;
//    @Resource
//    Article article;
    private static String respContent;

    public String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        BaseMessage baseMessage = null;
        // 默认返回的文本消息内容
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            String msgType = requestMap.get("MsgType");

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                baseMessage = dealTextMessage(requestMap);
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
                baseMessage = dealVoiceMessage(requestMap);
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
                respContent = "您发送的是小视频消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
            //将消息对象转换成xml
            if(baseMessage != null)
                respXml = MessageUtil.messageToXml(baseMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respXml;
    }

    /**
     * 处理语音消息
     * @param requestMap
     * @return
     */
    private BaseMessage dealVoiceMessage(Map<String, String> requestMap) {
        List<Article> list = new ArrayList<>();
        list.add(new Article("这是标题", "这是描述", "http://i0vlgub.hn3.mofasuidao.cn/app/img/my_find_img.jpg", "http://www.baidu.com"));
        return new NewsMessage(requestMap, "news", list);
    }

    /**
     * 处理文本消息
     * @param requestMap
     * @return
     */
    private BaseMessage dealTextMessage(Map<String, String> requestMap) {
        // 机器人聊天
        respContent = ChatService.Chat(requestMap.get("Content"));

        return new TextMessage(requestMap, MessageUtil.REQ_MESSAGE_TYPE_TEXT, respContent);
    }




}
