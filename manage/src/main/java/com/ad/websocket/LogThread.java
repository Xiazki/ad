package com.ad.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.util.HtmlUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by xiang on 2017/5/20.
 */
public class LogThread implements Runnable {

    private Process process;
    private WebSocketSession session;


    public LogThread(Process process, WebSocketSession session) {
        this.process = process;
        this.session = session;
    }

    @Override
    public void run() {
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            // 输出日志
            while ((line = reader.readLine()) != null) {
                session.sendMessage(new TextMessage(HtmlUtils.htmlEscape(line) + "<br>"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
