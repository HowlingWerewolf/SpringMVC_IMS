package springmvc_ims.repository.dao.testing;

import java.util.List;

import springmvc_ims.repository.dao.ProductDao;
import springmvc_ims.repository.model.Product;

public class InMemoryProductDao extends ProductDao {

    private List<Product> productList;

    public InMemoryProductDao(List<Product> productList) {
        this.productList = productList;
        logger.info("InMemoryProductDao constructor called");
    }
    
    @Override
    public List<Product> queryAllAsList() {
        return productList;
    }

    @Override
    public void save(Object product) {
    }

    @Override
    public void update(Object product) {
    }

    @Override
    public void delete(Object product) {
    }

}
