package com.smashtaps.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperShelfRequest {
    @NotBlank(message = "Shopper ID is required")
    private String shopperId;
    @NotEmpty(message = "Shelf must contain at least one product")
    private List<ShelfProductDTO> shelf;
}