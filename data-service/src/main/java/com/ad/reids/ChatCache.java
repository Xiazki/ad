package com.ad.reids;

import com.ad.bean.chat.ChatMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiang on 2017/5/21.
 */
@Component
public class ChatCache {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String CHAT_KEY = "chat_key:";

    @Autowired
    private RedisAdapter redisAdapter;

    public void addChat(ChatMessage message) {
        String value = JSON.toJSONString(message);
        try {
            redisAdapter.rpush(CHAT_KEY, value);
        } catch (Exception e) {
            logger.error("add chat error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<ChatMessage> getChats() {
        try {
            List<String> strings = redisAdapter.lrange(CHAT_KEY, 0, -1);
            return strings.stream().map(ChatMessage::from).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
