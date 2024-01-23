package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.inventoryservice.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
}
