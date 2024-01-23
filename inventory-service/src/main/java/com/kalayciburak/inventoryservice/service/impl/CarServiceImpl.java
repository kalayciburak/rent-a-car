package com.kalayciburak.inventoryservice.service.impl;

import com.kalayciburak.corepackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.CarResponse;
import com.kalayciburak.inventoryservice.repository.CarRepository;
import com.kalayciburak.inventoryservice.service.CarService;
import com.kalayciburak.inventoryservice.util.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.corepackage.model.response.GenericResponse.createSuccessResponse;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarMapper mapper;
    private final CarRepository repository;

    @Override
    public ResponseItem<List<CarResponse>> findAll() {
        var cars = repository.findAll();
        var data = cars.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, "Araçlar başarıyla listelendi.");
    }
}
