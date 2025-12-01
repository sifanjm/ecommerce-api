package com.smashtaps.service;

import com.smashtaps.dto.ProductMetadataRequest;
import com.smashtaps.model.ProductMetadata;
import com.smashtaps.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductMetadataService {
    private final ProductRepository productRepository;

    public void saveProductMetadata(ProductMetadataRequest request) {
        log.info("Saving product metadata for productId: {}", request.getProductId());

        ProductMetadata product = new ProductMetadata(
                request.getProductId(),
                request.getCategory(),
                request.getBrand()
        );

        productRepository.save(product);
        log.info("Product metadata saved successfully for productId: {}", request.getProductId());
    }

}
