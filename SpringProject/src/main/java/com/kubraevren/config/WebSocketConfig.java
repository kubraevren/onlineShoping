package com.kubraevren.config; // Örneğin, bu pakette olsun
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Mesaj broker'ı yapılandırması
        config.enableSimpleBroker("/topic"); // Mesajların gönderileceği hedef
        config.setApplicationDestinationPrefixes("/app"); // Uygulama endpoint'leri
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // WebSocket endpoint'ini tanımla
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }
}
