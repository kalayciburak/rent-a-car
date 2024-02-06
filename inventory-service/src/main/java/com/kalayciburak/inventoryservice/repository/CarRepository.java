package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonpackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entity.Car;
import com.kalayciburak.inventoryservice.repository.custom.CustomCarRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends BaseRepository<Car, Long>, CustomCarRepository {
    boolean existsByPlate(String plate);
}
