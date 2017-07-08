package springmvc_ims;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@ComponentScan(basePackages="springmvc_ims")
@Configuration
@EntityScan(basePackages="springmvc_ims.repository.model")
@EnableJpaRepositories(basePackages="springmvc_ims.repository.model.access")
@EnableTransactionManagement
public class AppConfig {
	
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
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      vendorAdapter.setGenerateDdl(true);

      LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
      factory.setJpaVendorAdapter(vendorAdapter);
      factory.setPackagesToScan("springmvc_ims.repository.model");
      factory.setDataSource(dataSource());
      factory.afterPropertiesSet();

      return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
      JpaTransactionManager txManager = new JpaTransactionManager();
      txManager.setEntityManagerFactory(entityManagerFactory());
      return txManager;
    }
    
    @Bean
    public Validator localValidatorFactoryBean() {
       return new LocalValidatorFactoryBean();
    }

}
/*
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans     
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context 
        http://www.springframework.org/schema/context/spring-context.xsd">

	<context:component-scan base-package="springmvc_ims.web.controller" />
    
    <bean id="productDaoImpl" class="springmvc_ims.repository.dao.ProductDaoImpl" />
    
    <bean id="productService" class="springmvc_ims.service.ProductService" />

    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basename" value="messages"/>
    </bean>
    
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"></property>
        <property name="prefix" value="/WEB-INF/pages/"></property>
        <property name="suffix" value=".jsp"></property>        
    </bean>
    
    <bean id="priceIncreaseValidator" class="springmvc_ims.web.validator.PriceIncreaseValidator" />
    
    <bean id="productValidator" class="springmvc_ims.web.validator.ProductValidator" />
    
    <bean id="scheduledTaskConfig" class="config.ScheduledTaskConfig" />
    
    <bean id="gratefulShutdowner" class="springmvc_ims.component.GratefulShutdowner" />

</beans>
*/

/*
	<display-name>Spring MVC Web Application</display-name>

	<servlet>
		<servlet-name>mvc-dispatcher</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>mvc-dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
	</servlet-mapping>

 	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/mvc-dispatcher-servlet.xml</param-value>
	</context-param> 
*/

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
