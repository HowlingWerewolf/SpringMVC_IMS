package com.ims;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
@EntityScan(basePackages="com.ims.data.model")
public class AppConfig {

//    @Bean
//    public MessageSource messageSource() {
//    	final var messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasenames("messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }

}


