package com.ims.service;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductDaoImpl productDaoImpl;
    
    public List<Product> getProducts() {
    	log.debug("Inside SimpleProductManager getProducts()");
        return productDaoImpl.queryAllAsList();
    }

    public void increasePrice(final int percentage) {
    	final List<Product> products = productDaoImpl.queryAllAsList();
        if (products != null) {
            for (final Product product : products) {
                double newPrice = product.getPrice() * (100 + percentage)/100;
                product.setPrice(newPrice);
                productDaoImpl.update(product);
            }
        }
    }

}
