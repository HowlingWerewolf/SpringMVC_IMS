package springmvc_ims.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springmvc_ims.repository.dao.ProductDaoImpl;
import springmvc_ims.repository.model.Product;

@Service
public class ProductService {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private ProductDaoImpl productDaoImpl;
    
    public List<Product> getProducts() {
    	logger.debug("Inside SimpleProductManager getProducts()");
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
