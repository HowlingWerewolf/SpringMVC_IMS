package com.ims;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.ims")
public class DispatcherConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver getViewResolver() {
        final var resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // Serve Angular dist files
        registry.addResourceHandler("/dist/**")
                .addResourceLocations("classpath:/dist/");
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
        
        // Serve Angular index.html
        registry.addResourceHandler("/index.html")
                .addResourceLocations("classpath:/dist/index.html");
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        // Forward unmapped routes to Angular's index.html for client-side routing
        registry.addViewController("/{spring:^(?!api|WEB-INF).*$}/**")
                .setViewName("forward:/index.html");
    }

}
