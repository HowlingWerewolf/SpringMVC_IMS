package springmvc_ims;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages="springmvc_ims.repository.model.access")
@EnableTransactionManagement
public class HibernateConfiguration {
	
	@Bean
	@Primary
	public DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .url("jdbc:postgresql://localhost:5432/springims")
	        .driverClassName("org.postgresql.Driver")
	        .username("springims")
	        .password("springims")
	        .build();
	}
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setGenerateDdl(true);

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(vendorAdapter);
      factory.setPackagesToScan("springmvc_ims.repository.model");
      factory.setDataSource(dataSource());
      factory.setJpaProperties(hibernateProperties());
      factory.afterPropertiesSet();

      return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(entityManagerFactory());
      return txManager;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.globally_quoted_identifiers", "true");
        properties.put("hibernate.max_fetch_depth", "2");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.jdbc.use_get_generated_keys", "true");
        properties.put("hibernate.show_sql", "true");
        properties.put("javax.persistence.validation.mode", "none");
        return properties;        
    }
}

/*
<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">
<hibernate-configuration>       
  <session-factory>
    <!-- Database connection settings -->
    <property name="connection.driver_class">org.postgresql.Driver</property>
    <property name="connection.url">jdbc:postgresql://localhost:5432/springims</property>
    <property name="connection.username">springims</property>
	<property name="connection.password">springims</property>
	<property name="hibernate.dialect">org.hibernate.dialect.PostgreSQL9Dialect</property>
	<property name="hibernate.globally_quoted_identifiers">true</property>
	<property name="hibernate.max_fetch_depth">2</property>
	<property name="hibernate.format_sql">true</property>
	<property name="hibernate.jdbc.use_get_generated_keys">true</property>
	<property name="show_sql">true</property>
  	<property name="javax.persistence.validation.mode">none</property>
    <mapping class="springmvc_ims.repository.model.Product"></mapping>
  </session-factory>
</hibernate-configuration>
*/