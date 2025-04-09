package com.ims.repository.dao;

import com.ims.repository.model.Product;
import com.ims.repository.model.access.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ProductDaoImpl {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void testDB() {
		Product product = new Product();
		product.setId(4);
		product.setDescription("Test");
		product.setPrice(1.0d);

		productRepository.save(product);
		
		List<Product> list = queryAllAsList();
        for(Product p : list) {
        	log.debug(p.getDescription().toString() + " " + p.getPrice());
        }
	}

	public List<Product> queryAllAsList() {
		log.info("Getting products!");
        List<Product> products = null;
        try {
        	products = productRepository.findAll();
		} catch (NullPointerException ex) {
			log.info("ProductDao: NPE!");
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
