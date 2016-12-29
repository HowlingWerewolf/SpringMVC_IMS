package springmvc_ims.db;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import springmvc_ims.model.Product;

public class DB {
	
	static Session session;
	
	public DB() {
		initSession();
	}
	
	@Override
	protected void finalize() throws Throwable {
		closeSession();
		super.finalize();
	}
	
	public void initSession() {
		session = HibernateUtil.getSessionFactory().openSession();	
	}
	
	public void closeSession() {
    	session.close();
	}
	
	public void saveProduct(Product product) {
		save(product);
	}

	public void save(Object o) {
		session.beginTransaction();	
		session.save(o);
		session.getTransaction().commit();
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
    	Query query = session.createQuery(queryString);
    	List<?> list = query.getResultList();	
    	return list;
	}
	
}
