package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.repository.model.Product;
import com.ims.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ProductAddFormControllerTest {

    @Mock
    ProductService productService;

    @Mock
    ProductDaoImpl productDao;

    @InjectMocks
    ProductAddFormController controller;

    @Test
    void testDisplayAndAddApi() {
        final var disp = controller.display();
        assertNotNull(disp);

        final Product p = Product.builder().id(1).description("x").price(1.0).build();
        doNothing().when(productDao).save(p);
        final var resp = controller.onSubmitApi(p);
        assertNotNull(resp);
        assertNotNull(controller.display());
    }

}