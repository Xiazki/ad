package com.ad.websocket;

import com.ad.bean.chat.ChatMessage;
import com.ad.common.constant.Constants;
import com.ad.ds.UserService;
import com.ad.entity.User;
import com.ad.reids.ChatCache;
import com.ad.reids.UserCache;
import com.ad.vo.PrincipalVo;
import org.apache.shiro.SecurityUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiang on 2017/5/21.
 */

public class ChatHandler extends TextWebSocketHandler {

    @Autowired
    private ChatCache chatCache;

    private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    @Autowired
    private UserService userService;

    @Autowired
    private UserCache userCache;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //存入在线队列
        if (sessionMap.get(session.getId()) == null) {
            sessionMap.put(session.getId(), session);
        }
        Long uid = Long.valueOf(HandlerUtil.parse(session.getUri().getQuery()).get("uid"));
        User user = userService.get(uid);
       userCache.addSocketUser(uid, user);
        userThreadLocal.set(user);
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


        Long uid = Long.valueOf(HandlerUtil.parse(session.getUri().getQuery()).get("uid"));
        //保存在缓存
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getPayload());
        chatMessage.setSendTime(new DateTime().toString(Constants.DATE_FORMAT));
        chatMessage.setUserName(userCache.getSocketUser(uid).getUsername());
        chatMessage.setUserId(userCache.getSocketUser(uid).getId());
        chatCache.addChat(chatMessage);
        //在线用户发送消息；先这样写 后期改成异步方式。
        for (Map.Entry<String, WebSocketSession> entry : sessionMap.entrySet()) {

            WebSocketSession online = entry.getValue();
            if (!online.getId().equals(session.getId())) {
                online.sendMessage(message);
            }

        }

        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //断线移除在线
        if (sessionMap.get(session.getId()) != null) {
            sessionMap.remove(session.getId());
        }
        super.afterConnectionClosed(session, status);
    }
}
