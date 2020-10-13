package com.ssafy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	 	@Override
	    // Ŭ���̾�Ʈ�� �޽����� ������ endpoint�� �����մϴ�.
	    public void configureMessageBroker(MessageBrokerRegistry config) {
	        config.enableSimpleBroker("/send");
	    }

	    @Override
	    // connection�� ������ CORS ����մϴ�.
	    public void registerStompEndpoints(StompEndpointRegistry registry) {
	    	System.out.println("config");
	        registry.addEndpoint("/").setAllowedOrigins("*").withSockJS();
	    }
}
