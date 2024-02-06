package com.kalayciburak.inventoryservice.repository.custom;

import com.kalayciburak.inventoryservice.model.entity.Car;

import java.util.List;

public interface CustomCarRepository {
    List<Car> findByStatus(String status);
}
