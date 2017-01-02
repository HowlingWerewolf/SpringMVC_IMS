package springmvc_ims.web.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import springmvc_ims.dao.ProductDao;
import springmvc_ims.model.Product;

public class SimpleProductManager implements ProductManager {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private ProductDao productDao;
    
    public List<Product> getProducts() {
    	logger.debug("Inside SimpleProductManager getProducts()");
        return productDao.queryAllAsList();
    }

    public void increasePrice(int percentage) {
    	List<Product> products = productDao.queryAllAsList();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                productDao.update(product);
            }
        }
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

}
