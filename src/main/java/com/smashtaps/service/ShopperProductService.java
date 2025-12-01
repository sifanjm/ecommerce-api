package com.smashtaps.service;

import com.smashtaps.dto.ShopperProductResponse;
import com.smashtaps.dto.ShopperShelfRequest;
import com.smashtaps.exception.ResourceNotFoundException;
import com.smashtaps.model.ProductMetadata;
import com.smashtaps.model.ShopperProductId;
import com.smashtaps.model.ShopperProductShelf;
import com.smashtaps.repository.ProductRepository;
import com.smashtaps.repository.ShopperProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopperProductService {
    private final ShopperProductRepository shopperProductRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void saveShopperShelf(ShopperShelfRequest request) {
        log.info("Saving shelf for shopperId: {}, products count: {}",
                request.getShopperId(), request.getShelf().size());

        String shopperId = request.getShopperId();


        shopperProductRepository.deleteByIdShopperId(shopperId);
        log.info("Deleted existing shelf data for shopperId: {}", shopperId);


        List<ShopperProductShelf> shelfEntries = request.getShelf().stream()
                .map(shelfProduct -> {
                    ShopperProductShelf shelf = new ShopperProductShelf();
                    shelf.setId(new ShopperProductId(shopperId, shelfProduct.getProductId()));
                    shelf.setRelevancyScore(shelfProduct.getRelevancyScore());

                    ProductMetadata product = productRepository
                            .findById(shelfProduct.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Product not found: " + shelfProduct.getProductId()));
                    shelf.setProduct(product);

                    return shelf;
                })
                .collect(Collectors.toList());

        shopperProductRepository.saveAll(shelfEntries);
        log.info("Shelf saved successfully for shopperId: {}", shopperId);
    }


    public List<ShopperProductResponse> getShopperProducts(
            String shopperId,
            String category,
            String brand,
            Integer limit) {

        log.info("Fetching products for shopperId: {}, category: {}, brand: {}, limit: {}",
                shopperId, category, brand, limit);


        int pageSize = validateLimit(limit);
        Pageable pageable = PageRequest.of(0, pageSize);


        List<ShopperProductShelf> shelves = shopperProductRepository.findShopperProducts(
                shopperId, category, brand, pageable
        );

        log.info("Found {} products for shopperId: {}", shelves.size(), shopperId);


        return shelves.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private int validateLimit(Integer limit) {
        if (limit == null) {
            return 10;
        }
        if (limit < 1) {
            return 10;
        }
        if (limit > 100) {
            return 100;
        }
        return limit;
    }

    private ShopperProductResponse toResponse(ShopperProductShelf shelf) {
        return new ShopperProductResponse(
                shelf.getId().getProductId(),
                shelf.getRelevancyScore(),
                shelf.getProduct().getCategory(),
                shelf.getProduct().getBrand()
        );
    }

}
