package com.ad.worker.handler;

import com.ad.worker.event.EventMoudle;

import java.util.List;

/**
 * Created by xiang on 2017/5/26.
 */
public class LogHandler implements EventHandler{
    public void doHandler(EventMoudle event) throws Exception {

    }

    public List<String> getSupportEventType() {
        return null;
    }
}
