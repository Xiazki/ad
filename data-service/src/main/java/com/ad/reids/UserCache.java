package com.ad.reids;

import com.ad.entity.User;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xiang on 2017/5/21.
 */
@Component
public class UserCache {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String SOCKET_KEY = "socket_key:";

    @Autowired
    public RedisAdapter redisAdapter;

    public void addSocketUser(Long userId, User user) {
        String value = JSON.toJSONString(user);
        try {
            redisAdapter.set(SOCKET_KEY + userId, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delSocketUser(Long userId) {
        String key = SOCKET_KEY + userId;
        try {
            redisAdapter.del(key);
        } catch (Exception e) {
            logger.error("delete key error :" + e.getMessage());
            e.printStackTrace();
        }
    }

    public User getSocketUser(Long userId) {
        try {
            String json = redisAdapter.get(SOCKET_KEY + userId);
            return JSON.parseObject(json, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
