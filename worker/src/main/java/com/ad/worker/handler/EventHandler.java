package com.ad.worker.handler;

import com.ad.worker.event.EventMoudle;

import java.util.List;


public interface EventHandler {
    void doHandler(EventMoudle event) throws Exception;

    List<String> getSupportEventType();
}
