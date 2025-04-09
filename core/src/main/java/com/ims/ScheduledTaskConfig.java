package com.ims;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Configuration
@EnableScheduling
@Service
@Slf4j
public class ScheduledTaskConfig {
	
    @Scheduled(fixedRate=60000)
    public void work() {
    	System.out.println(new Date() + ": Hello:)");
    	log.info(new Date() + ": Hello:)");
    	log.info("An info message. This should be written both to console and log file.");
    	log.debug("A debug message. This should be written only to log file.");
    	log.error("An error message. This should be written both to console, log file and error log file.");
    }
}
