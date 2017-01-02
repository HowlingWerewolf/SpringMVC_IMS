package springmvc_ims.web.service;

import java.io.Serializable;
import java.util.List;

import springmvc_ims.model.Product;

public interface ProductManager extends Serializable {

    public void increasePrice(int percentage);
    
    public List<Product> getProducts();
}
