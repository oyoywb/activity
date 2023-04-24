package com.oywb.weixin.activities.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.Session;

@Configuration
public class WebSocketConfig {

    /**
     * 注入一个ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /*private boolean userExist(Session session) {
        long userId = 0;
        Authentication authentication = (Authentication) session.getUserPrincipal();
        if (authentication == null) {
            log.error("用户未认证");
        } else {
            userId = userRepository.getUserIdByOpenId(authentication.getName());
        }
        return authentication != null && userId != 0;
    }*/

}
