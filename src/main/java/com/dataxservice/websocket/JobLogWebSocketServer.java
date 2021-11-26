package com.dataxservice.websocket;

import com.dataxservice.model.DataJob;
import com.dataxservice.model.DataJobLog;
import com.dataxservice.service.DataJobLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/websocket/{jobId}")
@Component
public class JobLogWebSocketServer {

    private static DataJobLogService dataJobLogService;

    @Autowired
    public void setDataJobLogService(DataJobLogService dataJobLogService) {
        JobLogWebSocketServer.dataJobLogService = dataJobLogService;
    }

    @PostConstruct
    public void init() {
        // System.out.println("websocket 加载");
        log.info("websocket 服务启动");
    }

    private static Logger log = LoggerFactory.getLogger(JobLogWebSocketServer.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();


    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "jobId") String jobId) {
        SessionSet.add(session);
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("有连接加入，当前连接数为：{}", cnt);
        log.info("用户请求的任务ID是: " + jobId);

        int currentLogId = 0;

        // 获取对应的job信息
        

        // 获取对应的job日志
        DataJobLog searchBean = new DataJobLog();
        DataJob datajob = new DataJob();
        datajob.setDataJobId(Integer.parseInt(jobId));
        searchBean.setDataJob(datajob);
        searchBean.setLogId(currentLogId);
        List<DataJobLog> logs = dataJobLogService.retrieveLogsBiggerThanSpecifiedLogId(searchBean);

        if (!CollectionUtils.isEmpty(logs)) {
            Iterator<DataJobLog> iterator = logs.iterator();
            while (iterator.hasNext()){
                SendMessage(session, iterator.next().getLogBody());
            }
        }

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        SessionSet.remove(session);
        // routeTable.remove(this.userId); //移除当前键值对
        int cnt = OnlineCount.decrementAndGet();
        log.info("有连接关闭，当前连接数为：{}", cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息：{}", message);
        SendMessage(session, "收到消息，消息内容：" + message);
    }

    /**
     * 出现错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误：{}，Session ID： {}", error.getMessage(), session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     *
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
//            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     *
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {
        for (Session session : SessionSet) {
            if (session.isOpen()) {
                SendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     *
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String message, String sessionId) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if (s.getId().equals(sessionId)) {
                session = s;
                break;
            }
        }
        if (session != null) {
            SendMessage(session, message);
        } else {
            log.warn("没有找到你指定ID的会话：{}", sessionId);
        }
    }
}
