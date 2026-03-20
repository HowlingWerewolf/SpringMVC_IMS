package com.ims.web.controller;

import com.ims.service.ProductService;
import com.ims.web.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("rawtypes")
class ProductDTOAddFormControllerTest {

    @Test
    void testDisplayAndAddApi() {
        // Create a stub ProductService that overrides save to do nothing
        final ProductService stubService = new ProductService(null, null) {
            @Override
            public void save(final com.ims.web.dto.ProductDTO productDTO) {
                // no-op stub
            }
        };

        final ProductAddFormController controller = new ProductAddFormController(stubService);

        final var disp = controller.display();
        assertNotNull(disp);

        final ProductDTO p = ProductDTO.builder().id(1).description("x").price(1.0).build();
        final var resp = controller.onSubmitApi(p);
        assertNotNull(resp);
        // Controller returns ResponseEntity.ok(Map.of("status","created")) -> 200
        assertEquals(200, resp.getStatusCode().value());
        assertNotNull(controller.display());
    }

}