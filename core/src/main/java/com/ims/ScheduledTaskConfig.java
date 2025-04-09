package com.ims;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Configuration
@EnableScheduling
@Service
public class ScheduledTaskConfig {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedRate=60000)
    public void work() {
    	System.out.println(new Date() + ": Hello:)");
    	logger.info(new Date() + ": Hello:)");
    	logger.info("An info message. This should be written both to console and log file.");
    	logger.debug("A debug message. This should be written only to log file.");
    	logger.error("An error message. This should be written both to console, log file and error log file.");
    }
}
