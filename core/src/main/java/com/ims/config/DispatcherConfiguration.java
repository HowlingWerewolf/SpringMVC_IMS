package com.ims.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.ims")
public class DispatcherConfiguration implements WebMvcConfigurer {

    /**
     * Serves static resources (index.html, JS, CSS) from the webapp root and common classpath locations.
     * IMPORTANT: do not register a catch-all pattern like '/**' because that will intercept
     * API paths (e.g. '/api/products') and Spring will attempt to serve them as static files
     * resulting in "No static resource" 404s. Instead register only the patterns that
     * correspond to the frontend static assets.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/index.html",
                        "/favicon.ico",
                        "/assets/**",
                        "/static/**",
                        "/webjars/**",
                        "/*.js",
                        "/*.css",
                        "/*.map",
                        "/*.svg",
                        "/*.png",
                        "/*.woff2",
                        "/*.woff",
                        "/*.ttf"
                )
                .addResourceLocations("/", "classpath:/META-INF/resources/", "classpath:/static/", "classpath:/public/")
                .setCachePeriod(0);
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        // Forward root to index.html so client-side routing can work
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        // Allow basic CORS for API paths (adjust origins in production)
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }

}
