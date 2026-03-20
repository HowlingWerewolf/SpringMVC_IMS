package com.ims.web.controller;

import com.ims.repository.dao.ProductDaoImpl;
import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ProductDTODeleteControllerTest {

    @Mock
    ProductService productService;

    @Mock
    ProductDaoImpl productDao;

    @InjectMocks
    ProductDeleteController controller;

    // FIXME AI
//    @Test
//    void testDeleteAndList() {
//        final var list = controller.list();
//        assertNotNull(list);
//
//        final ProductDTO p = ProductDTO.builder().id(999).description("x").price(1.0).build();
//        doNothing().when(productDao).delete(p);
//        final var resp = controller.deleteByBody(p);
//        assertNotNull(resp);
//    }

}