package springmvc_ims.repository.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class DB {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	static Session session;
	
	public void initSession() {
		session = HibernateUtil.getSessionFactory().openSession();	
	}
	
	public void closeSession() {
    	session.close();
	}

	public void save(Object o) {
		logger.debug("Saving...");
		initSession();
		
		try {
			session.beginTransaction();	
			session.save(o);
			session.getTransaction().commit();
		} finally {
			closeSession();
		}
	}

	public void delete(Object o) {
		logger.debug("Deleting...");
		initSession();
		
		try {
			session.beginTransaction();	
			session.delete(o);
			session.getTransaction().commit();
		} finally {
			closeSession();
		}
	}

	public void update(Object o) {
		logger.debug("Saving...");
		initSession();
		
		try {
			session.beginTransaction();	
			session.update(o);
			session.getTransaction().commit();
		} finally {
			closeSession();
		}
	}
	
	public Session getSession() {
		return session;
	}
	
	public List<?> query(String queryString) {		
		List<?> list = null;
		
		try {
			list = createQuery(queryString);
        } catch (Exception ex) {
        	logger.debug(ex.getMessage());
        }
		
		return list;
	}
	
	private List<?> createQuery(String queryString) {	
		initSession();
    	List<?> list = new ArrayList<>();	
    	
		try {
	    	Query query = session.createQuery(queryString);
	    	list = query.getResultList();
    	} catch (Exception ex) {
        	logger.debug(ex.getMessage());
        } finally {
			closeSession();
		}
    	return list;
	}
	
}
