package com.ims;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.ims")
public class DispatcherConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        // Serve static resources (index.html, JS, CSS) from the webapp root and common classpath locations
        registry.addResourceHandler("/**")
                .addResourceLocations("/", "classpath:/META-INF/resources/", "classpath:/static/", "classpath:/public/")
                .setCachePeriod(0);
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        // Forward root and any non-api, non-extension paths to index.html so client-side routing can work
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/{spring:[^\\.]*}").setViewName("forward:/index.html");
        // Removed problematic pattern "/**/{spring:[^\\.]*}" which is not supported by
        // the PathPatternParser (caused "No more pattern data allowed after **" errors).
        // A SPA-forwarding filter will handle multi-segment client-side routes instead.
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        // Allow basic CORS for API paths (adjust origins in production)
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

}
