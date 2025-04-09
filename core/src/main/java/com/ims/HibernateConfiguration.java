package com.ims;

import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.ims.repository.model.access")
@EnableTransactionManagement
@Slf4j
public class HibernateConfiguration {

    @Bean
    @Primary
    public DataSource dataSource() {
        log.info("Configuring datasource...");
        return DataSourceBuilder
                .create()
                .url("jdbc:postgresql://host.docker.internal:5432/postgres")
                .driverClassName("org.postgresql.Driver")
                .username("springims")
                .password("springims")
                .build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final var vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.ims.repository.model");
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        final var txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    private Properties hibernateProperties() {
        final var properties = new Properties();
//        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.globally_quoted_identifiers", "true");
        properties.put("hibernate.max_fetch_depth", "2");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.jdbc.use_get_generated_keys", "true");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("javax.persistence.validation.mode", "none");
        return properties;
    }
}
