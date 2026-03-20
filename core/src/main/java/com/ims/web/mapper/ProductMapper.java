package com.ims.web.mapper;

import com.ims.data.model.Product;
import com.ims.web.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO map(final Product product) {
        if (product == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public Product map(final ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        return Product.builder()
                .id(productDTO.getId())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }

}
