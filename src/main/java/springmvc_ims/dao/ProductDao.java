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

		db.saveProduct(product);
		
		List<Product> list = queryAllAsList();
        for(Product p : list) {
        	System.out.println(p.getDescription().toString() + " " + p.getPrice());
        }
	}

    public List<Product> queryAllAsList() {
        logger.info("Getting products!");
		List<Product> products = (List<Product>) db.query("FROM Product");
        return products;
    }

    public void save(Object product) {
    	db.saveProduct((Product) product);
    }
    
}
