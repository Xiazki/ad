package com.ad.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by xiang on 2017/5/8.
 */
@Component
public class RedisAdapter {

    @Autowired
    private JedisPool jedisPool;

    public String set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.set(key, value);
        } catch (Exception e) {
            throw e;//for cache log
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public String get(String key) throws Exception{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            throw  e;
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
