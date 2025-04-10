package com.ims;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EntityScan(basePackages="com.ims.repository.model")
public class AppConfig {
    
    @Bean
    public Validator localValidatorFactoryBean() {
       return new LocalValidatorFactoryBean();
    }
    
    @Bean
    public MessageSource messageSource() {
    	final var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}


