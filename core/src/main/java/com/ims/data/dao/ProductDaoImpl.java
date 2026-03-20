package com.ims.data.dao;

import com.ims.data.model.Product;
import com.ims.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductDaoImpl {

    private final ProductRepository productRepository;

    public List<Product> queryAllAsList() {
        log.info("Getting products!");
        List<Product> products = null;
        try {
            products = productRepository.findAll();
        } catch (final NullPointerException ex) {
            log.info("ProductDao: NPE!");
        }
        return products;
    }

    public void save(final Product product) {
        productRepository.save(product);
    }

    public void delete(final Product product) {
        productRepository.delete(product);
    }

    public void deleteById(final Integer id) {
        productRepository.deleteById(id);
    }

    public void update(final Product product) {
        save(product);
    }

}
