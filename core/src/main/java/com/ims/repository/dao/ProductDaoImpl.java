package com.ims.repository.dao;

import com.ims.repository.model.Product;
import com.ims.repository.model.access.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ProductDaoImpl {

    private final ProductRepository productRepository;

    public void testDB() {
        final Product product = Product.builder()
                .id(4)
                .description("Test")
                .price(1.0d).build();

        productRepository.save(product);

        final List<Product> list = queryAllAsList();
        for (final Product p : list) {
            log.debug(p.getDescription() + " " + p.getPrice());
        }
    }

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

    public void update(final Product product) {
        save(product);
    }

}
