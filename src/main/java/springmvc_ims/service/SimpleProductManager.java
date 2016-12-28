package springmvc_ims.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import springmvc_ims.model.Product;

public class SimpleProductManager implements ProductManager {

	protected final Log logger = LogFactory.getLog(getClass());
    private List<Product> products;
    
    public List<Product> getProducts() {
    	logger.debug("Inside SimpleProductManager getProducts()");
        return products;
    }

    public void increasePrice(int percentage) {
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
            }
        }
    }
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
