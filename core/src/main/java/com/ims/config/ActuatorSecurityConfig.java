package com.ims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ActuatorSecurityConfig {

    @Bean
    public SecurityFilterChain actuatorSecurity(HttpSecurity http) {
        // TODO: Make actuator endpoints secure for production (restrict to admin role / separate management port)
        // For now, permit all access to /actuator/** as requested.
        // Use servlet path matching so this works whether the app is run embedded or deployed
        // under a servlet context path (e.g. /SpringMVC_IMS). We check the servlet path which
        // excludes the container context path.
        http
            .requestMatcher(request -> {
                String servletPath = request.getServletPath();
                return servletPath != null && servletPath.startsWith("/actuator");
            })
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
