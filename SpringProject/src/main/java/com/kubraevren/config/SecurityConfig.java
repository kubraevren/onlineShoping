package com.kubraevren.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests() // Modern metot ismi
                .anyRequest().permitAll() // Tüm isteklere izin verir
            .and()
            .csrf().disable(); // CSRF korumasını devre dışı bırakır
        
        return http.build();
    }
}
