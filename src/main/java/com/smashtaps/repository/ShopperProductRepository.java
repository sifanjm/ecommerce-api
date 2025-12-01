package com.smashtaps.repository;

import com.smashtaps.model.ShopperProductId;
import com.smashtaps.model.ShopperProductShelf;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperProductRepository extends JpaRepository<ShopperProductShelf, ShopperProductId> {

    @Query("SELECT s FROM ShopperProductShelf s " +
            "JOIN FETCH s.product p " +
            "WHERE s.id.shopperId = :shopperId " +
            "AND (:category IS NULL OR p.category = :category) " +
            "AND (:brand IS NULL OR p.brand = :brand) " +
            "ORDER BY s.relevancyScore DESC")
    List<ShopperProductShelf> findShopperProducts(
            @Param("shopperId") String shopperId,
            @Param("category") String category,
            @Param("brand") String brand,
            Pageable pageable
    );

    void deleteByIdShopperId(String shopperId);

}
