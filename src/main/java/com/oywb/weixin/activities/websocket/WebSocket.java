package com.oywb.weixin.activities.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oywb.weixin.activities.dao.MessageHistoryRepository;
import com.oywb.weixin.activities.dao.UserRepository;
import com.oywb.weixin.activities.entity.MessageHistoryEntity;
import com.oywb.weixin.activities.event.MessageEventPublisher;
import com.oywb.weixin.activities.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 前后端交互的类实现消息的接收推送
 *
 * @ServerEndpoint(value = "/ws/{userId}") 前端通过此URI 和后端交互，建立连接,userId为用户id
 */
@Slf4j
@ServerEndpoint(value = "/ws")
@Component
public class WebSocket {

    /** 记录当前在线连接数 */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /** 存放所有在线的客户端 */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    private static UserService userService;
    private static MessageEventPublisher messageEventPublisher;
    private static MessageHistoryRepository messageHistoryDao;

    @Autowired
    public void  setUserRepository(UserService userService) {
        WebSocket.userService = userService;
    }
    @Autowired
    public void setMessageEventPublisher(MessageEventPublisher messageEventPublisher) {
        WebSocket.messageEventPublisher = messageEventPublisher;
    }

    @Autowired
    public void setMessageHistoryDao(MessageHistoryRepository messageHistoryDao) {
        WebSocket.messageHistoryDao = messageHistoryDao;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen

    public void onOpen(Session session) {
        Authentication authentication = (Authentication) session.getUserPrincipal();
        if (authentication == null) {
            log.error("用户未认证");
        } else {
            long userId = userService.getUserId(authentication.getName());
            session.getUserProperties().put("userId", userId);
            onlineCount.incrementAndGet(); // 在线数加1
            clients.put(authentication.getName(), session);
            log.info("有新连接加入:{}，当前在线人数为:{},用户id:{}", session.getId(), onlineCount.get(),userId);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        Authentication authentication = (Authentication) session.getUserPrincipal();
        if (authentication == null) {
            log.error("用户未认证");
        } else {
            onlineCount.decrementAndGet(); // 在线数减1
            clients.remove(authentication.getName());
            log.info("有一连接关闭:{}，当前在线人数为:{}", session.getId(), onlineCount.get());
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     * 客户端发送过来的消息
     * 这里能过地址获取用户userId与ws session关联至map中
     */
    //{"message": "test2", "toOpenId": "oBxyZ1KBsZ88_MsOgSanrKcrFqFE", "toUserId": 2}
    @OnMessage
    public void onMessage(String message, Session session) {
        Authentication authentication = (Authentication) session.getUserPrincipal();
        if (authentication == null) {
            log.error("用户未认证");
        } else {
            String openId = authentication.getName();
            long userId = (long) clients.get(openId).getUserProperties().get("userId");
            log.info("服务端收到客户端[{}]的消息[{}]", session.getId(), message);
            try {
                JSONObject myMessage = JSON.parseObject(message);
                if (myMessage != null) {
                    Session toSession = null;
                    if(myMessage.getString("toOpenId") != null){
                        toSession = clients.get(myMessage.getString("toOpenId")); //取到接收userId session
                    }
                    if (toSession != null) {
                        this.sendMessage(myMessage.get("message").toString(), toSession);
                    }
                    //else{

                        //log.info("type:"+myMessage.getString("type"));
                        //if (myMessage.getString("type").equals("all")){
                            //群发
                        //    this.sendMessageAll(myMessage.get("message").toString(),userId);
                        //}else{
                        //     log.error("session不存在");
                        //}

                    //}
                    long toUserId = myMessage.getLong("toUserId");
                    MessageHistoryEntity messageHistoryEntity = new MessageHistoryEntity();
                    messageHistoryEntity.setContext(myMessage.get("message").toString());
                    messageHistoryEntity.setReceiver(toUserId);
                    messageHistoryEntity.setSender(userId);
                    messageHistoryEntity.setTs(new Timestamp(System.currentTimeMillis()));
                    messageHistoryDao.save(messageHistoryEntity);
                }
            } catch (Exception e) {
                log.error("解析失败:{}", e);
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息[{}]", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败:{}", e);
        }
    }
    /**
     * 群发消息
     *
     * @param message
     * 消息内容
     */
    private void sendMessageAll(String message, String userId) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            Session toSession = sessionEntry.getValue();
            // 排除掉自己
            if (!userId.equals(sessionEntry.getKey())) {
                //log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
                log.info("服务端给客户端用户id:[{}]发送消息{}", userId, message);
                toSession.getAsyncRemote().sendText(message);
            }
        }
    }


}