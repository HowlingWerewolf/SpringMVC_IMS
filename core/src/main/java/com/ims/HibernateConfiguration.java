package com.ims;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.ims.data.repository")
@EnableTransactionManagement
public class HibernateConfiguration {

    @Bean
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .url("jdbc:postgresql://host.docker.internal:5432/postgres")
                .driverClassName("org.postgresql.Driver")
                .username("springims")
                .password("springims")
                .build();
    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        final var vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.ims.data.model");
//        factory.setDataSource(dataSource());
//        factory.setJpaProperties(hibernateProperties());
//        factory.afterPropertiesSet();
//
//        return factory.getObject();
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        final var txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
//
//    private Properties hibernateProperties() {
//        final var properties = new Properties();
//        properties.put("hibernate.globally_quoted_identifiers", "true");
//        properties.put("hibernate.max_fetch_depth", "2");
//        properties.put("hibernate.format_sql", "true");
//        properties.put("hibernate.jdbc.use_get_generated_keys", "true");
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("javax.persistence.validation.mode", "none");
//        return properties;
//    }
}
