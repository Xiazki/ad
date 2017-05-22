package com.ad.bean.chat;

import com.alibaba.fastjson.JSON;

/**
 * Created by xiang on 2017/5/21.
 */
public class ChatMessage {

    private String userName;
    private Long userId;
    private String sendTime;
    private String content;

    public static ChatMessage from(String message) {
        return JSON.parseObject(message, ChatMessage.class);
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
