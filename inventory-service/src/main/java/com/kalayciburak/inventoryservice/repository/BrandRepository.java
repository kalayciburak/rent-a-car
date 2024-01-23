package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.inventoryservice.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
