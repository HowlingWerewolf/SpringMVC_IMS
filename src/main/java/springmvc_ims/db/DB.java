package springmvc_ims.db;

import org.hibernate.Session;

import springmvc_ims.model.Product;

public class DB {
	
	public static void initDB(){
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Product product = new Product();
		product.setId(4);
		product.setDescription("Test");
		product.setPrice(1.0d);

		session.save(product);
		session.getTransaction().commit();
	}

}
