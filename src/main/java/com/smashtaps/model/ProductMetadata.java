package com.smashtaps.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "product_metadata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMetadata {

    @Id
    @Column(name = "product_id", length = 50)
    private String productId;

    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "brand", length = 100)
    private String brand;
}
