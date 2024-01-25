package com.kalayciburak.inventoryservice.repository;

import com.kalayciburak.commonpackage.repository.SimpleRepository;
import com.kalayciburak.inventoryservice.model.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends SimpleRepository<City, Long> {
}
