package com.smashtaps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShelfProductDTO {
    @NotBlank(message = "Product ID is required")
    private String productId;
    @NotNull(message = "Relevancy score is required")
    private BigDecimal relevancyScore;
}
