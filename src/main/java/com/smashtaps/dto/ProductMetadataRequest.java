package com.smashtaps.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMetadataRequest {
    @NotBlank(message = "Product ID is required")
    private String productId;
    @NotBlank(message = "Category is required")
    private String category;
    @NotBlank(message = "Brand is required")
    private String brand;
}
