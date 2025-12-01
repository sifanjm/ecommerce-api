package com.smashtaps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProductResponse {
    private String productId;
    private BigDecimal relevancyScore;
    private String category;
    private String brand;
}
