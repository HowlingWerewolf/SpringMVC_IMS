package springmvc_ims.service;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import springmvc_ims.db.HibernateUtil;

@Component
public class GratefulShutdowner implements ApplicationListener<ContextClosedEvent> {
	
	@Override
	public void onApplicationEvent(ContextClosedEvent e) {
		HibernateUtil.shutdown();
	}
	
}
