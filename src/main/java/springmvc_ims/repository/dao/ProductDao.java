package springmvc_ims.repository.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import springmvc_ims.repository.model.Product;

@Repository
public class ProductDao extends Dao {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void testDB() {		
		Product product = new Product();
		product.setId(4);
		product.setDescription("Test");
		product.setPrice(1.0d);

		db.save(product);
		
		List<Product> list = queryAllAsList();
        for(Product p : list) {
        	logger.debug(p.getDescription().toString() + " " + p.getPrice());
        }
	}

	@SuppressWarnings("unchecked")
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

	@Override
	public void update(Object product) {
		db.update((Product) product);
	}
    
}
