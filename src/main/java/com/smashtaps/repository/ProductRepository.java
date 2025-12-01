package com.smashtaps.repository;

import com.smashtaps.model.ProductMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductMetadata, String> {
}
