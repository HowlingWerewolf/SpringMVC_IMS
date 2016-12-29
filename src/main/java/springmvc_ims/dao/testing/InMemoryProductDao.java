package springmvc_ims.dao.testing;

import java.util.List;

import springmvc_ims.dao.ProductDao;
import springmvc_ims.model.Product;

public class InMemoryProductDao extends ProductDao {

    private List<Product> productList;

    public InMemoryProductDao(List<Product> productList) {
        this.productList = productList;
    }
    
    @Override
    public List<Product> queryAllAsList() {
        return productList;
    }

    @Override
    public void save(Object product) {
    }

}
