package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonpackage.repository.BaseRepository;
import com.kalayciburak.inventoryservice.model.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends BaseRepository<Car, Long> {
    List<Car> findByModelId(Long id);
}
