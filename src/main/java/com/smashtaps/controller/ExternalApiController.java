package com.smashtaps.controller;


import com.smashtaps.dto.ShopperProductResponse;
import com.smashtaps.service.ShopperProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/external")
@RequiredArgsConstructor
@Slf4j
public class ExternalApiController {

    private final ShopperProductService shopperProductService;


    @GetMapping("/shoppers/{shopperId}/products")
    public ResponseEntity<List<ShopperProductResponse>> getShopperProducts(
            @PathVariable String shopperId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Integer limit) {

        log.info("Received request to get products for shopperId: {}, category: {}, brand: {}, limit: {}",
                shopperId, category, brand, limit);

        List<ShopperProductResponse> products = shopperProductService.getShopperProducts(
                shopperId, category, brand, limit
        );

        return ResponseEntity.ok(products);
    }
}