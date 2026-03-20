package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductDTODeleteControllerTest {

    @Test
    void testDeleteAndList() {
        // create a stub ProductService implementation
        final ProductService stubService = new ProductService(null, null) {
            @Override
            public java.util.List<com.ims.repository.model.Product> getProducts() {
                return Collections.emptyList();
            }

            @Override
            public void delete(final com.ims.web.dto.ProductDTO productDTO) {
                // no-op
            }
        };

        final ProductDeleteController controller = new ProductDeleteController(stubService);

        final var list = controller.list();
        assertNotNull(list);

        final ProductDTO p = ProductDTO.builder().id(999).description("x").price(1.0).build();
        final var resp = controller.deleteByBody(p);
        assertNotNull(resp);
        assertEquals(200, resp.getStatusCode().value());
    }

}