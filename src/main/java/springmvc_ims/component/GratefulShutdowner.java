package springmvc_ims.component;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import springmvc_ims.repository.db.HibernateUtil;

@Component
public class GratefulShutdowner implements ApplicationListener<ContextClosedEvent> {
	
    /** Logger for this class and subclasses */
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    
	@Override
	public void onApplicationEvent(ContextClosedEvent e) {
		shutdownHibernate();
		deregisterJdbcDrivers();
	}
	
	private void shutdownHibernate() {
		HibernateUtil.shutdown();		
	}
	
	private void deregisterJdbcDrivers() {
        // This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException ex) {
                logger.error(String.format("Error deregistering driver %s", driver));
            }

        }
	}
	
}
