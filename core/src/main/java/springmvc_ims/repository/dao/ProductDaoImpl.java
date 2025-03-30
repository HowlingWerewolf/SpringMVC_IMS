package springmvc_ims.repository.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import springmvc_ims.repository.model.Product;
import springmvc_ims.repository.model.access.ProductRepository;

@Repository
public class ProductDaoImpl {
	
	@Autowired
	private ProductRepository productRepository;
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void testDB() {		
		Product product = new Product();
		product.setId(4);
		product.setDescription("Test");
		product.setPrice(1.0d);

		productRepository.save(product);
		
		List<Product> list = queryAllAsList();
        for(Product p : list) {
        	logger.debug(p.getDescription().toString() + " " + p.getPrice());
        }
	}

	public List<Product> queryAllAsList() {
        logger.info("Getting products!");
        List<Product> products = null;
        try {
        	products = productRepository.findAll();
		} catch (NullPointerException ex) {
	        logger.info("ProductDao: NPE!");
		}
        return products;
    }

	public void save(Product product) {
		productRepository.save(product);
    }

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public void update(Product product) {
		save(product);
	}
    
}
