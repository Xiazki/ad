package com.ad.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

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

    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long rpush(String key, String value) throws Exception {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.rpush(key, value);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    public long lpush(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> brpop(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.brpop(0, key);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> lrange(String key, int start, int end) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.lrange(key, start, end);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void del(String key) throws Exception{
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            throw e;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
