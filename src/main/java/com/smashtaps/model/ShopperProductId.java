package com.smashtaps.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProductId implements Serializable {
    @Column(name = "shopper_id", length = 50)
    private String shopperId;

    @Column(name = "product_id", length = 50)
    private String productId;
}
