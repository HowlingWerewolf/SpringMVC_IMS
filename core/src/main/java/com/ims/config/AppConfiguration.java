package com.ims.config;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages="com.ims.data.model")
public class AppConfiguration {
}


