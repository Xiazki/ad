package com.ad.worker;

import com.ad.reids.RedisAdapter;
import com.ad.worker.event.EventMoudle;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;


public class EventProducerImpl implements EventProducer{

    @Autowired
    private RedisAdapter redisAdapter;

    public void sendEvent(EventMoudle event) throws Exception {
        String eventJson = JSON.toJSONString(event, SerializerFeature.DisableCircularReferenceDetect);
        String key = RedisAdapter.getEventKey();
        redisAdapter.lpush(key,eventJson);
    }
}
