package com.ims;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@Slf4j
public class App extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    @Override
    public void onStartup(final ServletContext servletContext) {
        final Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://host.docker.internal:5432/postgres",
                        "postgres", "example")
                .validateMigrationNaming(true)
                .load();

        log.info("Starting baseline table setup");
        flyway.baseline();

        log.info("Starting migration");
        flyway.migrate();
    }
}
