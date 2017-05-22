package com.ad.websocket;

import com.ad.ds.project.ProjectService;
import com.ad.entity.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiang on 2017/5/20.
 * 日志输出处理器
 */
public class LogHandler extends AbstractWebSocketHandler {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;


    /**
     * 完成连接,输出日志
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long projectId = Long.valueOf(HandlerUtil.parse(session.getUri().getQuery()).get("projectId"));
        Project project = projectService.getById(projectId);
        //测试 使用bat脚本测试
        String command = "cmd /c D:/Test/deploy.bat " + project.getProjectName();
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        LogThread logThread = new LogThread(process, session);
        poolTaskExecutor.execute(logThread);
       // new Thread(logThread).start();

//        session.sendMessage(new TextMessage("test:" + projectId));
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

}
