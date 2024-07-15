package com.kalayciburak.filterservice.repository;

import com.kalayciburak.filterservice.model.entity.Car;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends MongoRepository<Car, String> {
    void deleteByCarId(Long carId);

    void deleteByBrandId(Long brandId);

    Optional<Car> findByCarId(Long carId);

    List<Car> findAllByBrandId(Long brandId);
}
