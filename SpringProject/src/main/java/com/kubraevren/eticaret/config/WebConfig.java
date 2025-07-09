package com.kubraevren.eticaret.config; // Örneğin, bu pakette olsun

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/uploads/**" URL isteğini proje kökündeki "uploads" klasörüne yönlendirir
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
