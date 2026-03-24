package com.ims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ActuatorSecurityConfiguration {

    @Bean
    public SecurityFilterChain actuatorSecurity(HttpSecurity http) {
        // Permits all access to /actuator/**
        http
            .securityMatcher("/actuator/**")
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        return http.build();
    }
}
