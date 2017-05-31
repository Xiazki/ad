package com.ad.worker;

import com.ad.worker.event.EventMoudle;


public interface EventProducer {
    void sendEvent(EventMoudle event) throws Exception;
}
