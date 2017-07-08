package springmvc_ims.repository.dao.testing;

import java.util.List;

import springmvc_ims.repository.dao.ProductDaoImpl;
import springmvc_ims.repository.model.Product;

public class InMemoryProductDaoImpl extends ProductDaoImpl {

    private List<Product> productList;

    public InMemoryProductDaoImpl(List<Product> productList) {
        this.productList = productList;
        logger.info("InMemoryProductDao constructor called");
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
