package springmvc_ims.service;

import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTaskConfig {

    @Scheduled(fixedRate=120000)
    public void work() {
    	System.out.println(new Date() + ": Hello:)");
    }
}
