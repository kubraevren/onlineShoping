package com.kubraevren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.AbstractMessageChannel;
import org.springframework.messaging.support.ExecutorSubscribableChannel;

@Configuration
public class AppConfig {

    @Bean
    @Primary    
    public SimpMessagingTemplate simpMessagingTemplate() {
        // Gerekli bir MessageChannel oluşturuyoruz
        AbstractMessageChannel messageChannel = new ExecutorSubscribableChannel();
        return new SimpMessagingTemplate(messageChannel);
    }
}
