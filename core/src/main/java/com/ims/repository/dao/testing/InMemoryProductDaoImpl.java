package com.ims.repository.dao.testing;

import java.util.List;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InMemoryProductDaoImpl extends ProductDaoImpl {

    private List<Product> productList;

    public InMemoryProductDaoImpl(List<Product> productList) {
        this.productList = productList;
        log.info("InMemoryProductDao constructor called");
    }
    
    @Override
    public List<Product> queryAllAsList() {
        return productList;
    }

    @Override
    public void save(Product product) {
    }

    @Override
    public void update(Product product) {
    }

    @Override
    public void delete(Product product) {
    }

}
