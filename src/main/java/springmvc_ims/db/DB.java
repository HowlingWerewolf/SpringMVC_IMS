package springmvc_ims.db;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

public class DB {
	
	static Session session;
	
	public void initSession() {
		session = HibernateUtil.getSessionFactory().openSession();	
	}
	
	public void closeSession() {
    	session.close();
	}

	public void save(Object o) {
		System.out.println("Saving...");
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
		System.out.println("Deleting...");
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
		System.out.println("Saving...");
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
        	System.out.println(ex.getMessage());
        	System.out.println(ex.getStackTrace());
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
        	System.out.println(ex.getMessage());
        	System.out.println(ex.getStackTrace()); 
        } finally {
			closeSession();
		}
    	return list;
	}
	
}
