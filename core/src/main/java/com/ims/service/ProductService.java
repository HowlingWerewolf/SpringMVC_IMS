package com.ims.service;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.web.dto.ProductDTO;
import com.ims.web.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductDaoImpl productDaoImpl;
    private final ProductMapper productMapper;
    
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

    public void save(final ProductDTO productDTO) {
        productDaoImpl.save(productMapper.map(productDTO));
    }

    public void delete(final ProductDTO productDTO) {
        productDaoImpl.delete(productMapper.map(productDTO));
    }

    public void delete(final Product product) {
        productDaoImpl.delete(product);
    }

    public void deleteByID(final Integer id) {
        productDaoImpl.deleteById(id);
    }

}
