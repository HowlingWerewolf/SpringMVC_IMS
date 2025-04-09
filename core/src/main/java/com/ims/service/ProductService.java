package com.ims.service;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

	@Autowired
    private ProductDaoImpl productDaoImpl;
    
    public List<Product> getProducts() {
    	log.debug("Inside SimpleProductManager getProducts()");
        return productDaoImpl.queryAllAsList();
    }

    public void increasePrice(int percentage) {
    	List<Product> products = productDaoImpl.queryAllAsList();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                productDaoImpl.update(product);
            }
        }
    }

}
