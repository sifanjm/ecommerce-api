package com.smashtaps.controller;

import com.smashtaps.dto.ProductMetadataRequest;
import com.smashtaps.dto.ShopperShelfRequest;
import com.smashtaps.service.ProductMetadataService;
import com.smashtaps.service.ShopperProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
@Slf4j
public class InternalApiController {

    private final ProductMetadataService productMetadataService;
    private final ShopperProductService shopperProductService;


    @PostMapping("/products/metadata")
    public ResponseEntity<Map<String, String>> saveProductMetadata(
            @Valid @RequestBody ProductMetadataRequest request) {

        log.info("Received request to save product metadata: {}", request.getProductId());

        productMetadataService.saveProductMetadata(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Product metadata saved successfully");
        response.put("productId", request.getProductId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/shoppers/personalized-products")
    public ResponseEntity<Map<String, String>> saveShopperShelf(
            @Valid @RequestBody ShopperShelfRequest request) {

        log.info("Received request to save shopper shelf for shopperId: {}", request.getShopperId());

        shopperProductService.saveShopperShelf(request);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Shopper shelf saved successfully");
        response.put("shopperId", request.getShopperId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}