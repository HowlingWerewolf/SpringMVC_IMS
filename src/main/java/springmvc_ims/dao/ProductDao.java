package springmvc_ims.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import springmvc_ims.model.Product;

public class ProductDao extends Dao {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public void testDB() {		
		Product product = new Product();
		product.setId(4);
		product.setDescription("Test");
		product.setPrice(1.0d);

		db.save(product);
		
		List<Product> list = queryAllAsList();
        for(Product p : list) {
        	System.out.println(p.getDescription().toString() + " " + p.getPrice());
        }
	}

	@Override
    public List<Product> queryAllAsList() {
        logger.info("Getting products!");
        List<Product> products = null;
        try {
        	products = (List<Product>) db.query("FROM Product");
		} catch (NullPointerException ex) {
	        logger.info("ProductDao: NPE!");
		}
        return products;
    }

	@Override
    public void save(Object product) {
    	db.save((Product) product);
    }

	@Override
	public void delete(Object product) {
		db.delete((Product) product);
	}
    
}
