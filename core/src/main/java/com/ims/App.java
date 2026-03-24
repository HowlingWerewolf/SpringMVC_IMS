package com.ims;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

@SpringBootApplication
@Slf4j
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        // Ensure embedded runs use the desired context path so actuator appears under
        // /SpringMVC_IMS when started via main() or mvn spring-boot:run.
        app.setDefaultProperties(java.util.Map.of("server.servlet.context-path", "/SpringMVC_IMS"));
        app.run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady(ApplicationReadyEvent event) {
        Environment env = event.getApplicationContext().getEnvironment();
        String port = env.getProperty("local.server.port", env.getProperty("server.port", "8080"));
        String ctx = env.getProperty("server.servlet.context-path", "");
        if (ctx == null || ctx.isBlank()) {
            ctx = "";
        }
        log.info("Application ready. Actuator base URL: http://localhost:" + port + ctx + "/actuator");

        doFlywayMigration(env);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        // Ensure Spring Boot's startup logic runs so the WebApplicationContext and
        // associated listeners (ContextLoaderListener, DispatcherServlet registration, etc.)
        // are properly registered when running as a WAR in an external servlet container.
        // Do not swallow exceptions here: if onStartup fails, the application should
        // fail fast so the missing root WebApplicationContext error is visible
        // and the deployment can be corrected.
        super.onStartup(servletContext);
    }

    /**
     * Ensure a root WebApplicationContext is available via a ContextLoaderListener.
     * Some actuator endpoints (for example the servlet-based operations such as
     * /actuator/mappings) look up the root WebApplicationContext from the
     * ServletContext attribute. If none is present (e.g. in some WAR setups),
     * register it here so actuator works consistently.
     */
    @Bean
    public ServletContextInitializer registerRootContext(ApplicationContext applicationContext) {
        return servletContext -> {
            if (WebApplicationContextUtils.getWebApplicationContext(servletContext) == null) {
                if (applicationContext instanceof WebApplicationContext webCtx) {
                    servletContext.addListener(new ContextLoaderListener(webCtx));
                    log.info("Registered ContextLoaderListener with application WebApplicationContext");
                } else {
                    log.warn("ApplicationContext is not a WebApplicationContext; cannot register ContextLoaderListener");
                }
            } else {
                log.debug("Root WebApplicationContext already present; no ContextLoaderListener registered");
            }
        };
    }

    private void doFlywayMigration(Environment env) {
        // Prefer explicit Flyway properties, then fall back to datasource properties.
        String url = env.getProperty("spring.flyway.url",
                env.getProperty("spring.datasource.url", "jdbc:postgresql://host.docker.internal:5432/postgres"));
        String user = env.getProperty("spring.flyway.user",
                env.getProperty("spring.datasource.username", "postgres"));
        String pass = env.getProperty("spring.flyway.password",
                env.getProperty("spring.datasource.password", "example"));

        if (url == null || url.isBlank()) {
            log.warn("No Flyway JDBC URL configured; skipping Flyway migrations");
            return;
        }

        final Flyway flyway = Flyway.configure()
                .dataSource(url, user, pass)
                .validateMigrationNaming(true)
                .load();

        log.info("Starting baseline table setup");
        flyway.baseline();

        log.info("Starting migration");
        flyway.migrate();
    }

}
