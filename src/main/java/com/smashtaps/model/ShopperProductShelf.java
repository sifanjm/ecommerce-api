package com.smashtaps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "shopper_product_shelf")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopperProductShelf {
    @EmbeddedId
    private ShopperProductId id;

    @Column(name = "relevancy_score", precision = 10, scale = 2)
    private BigDecimal relevancyScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductMetadata product;
}
