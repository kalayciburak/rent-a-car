package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.CarRequest;
import com.kalayciburak.inventoryservice.model.dto.response.CarResponse;
import com.kalayciburak.inventoryservice.model.entity.Car;
import com.kalayciburak.inventoryservice.repository.CarRepository;
import com.kalayciburak.inventoryservice.util.mapper.CarMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Car.*;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarMapper mapper;
    private final CarRepository repository;

    public ResponseItem<CarResponse> findById(Long id) {
        var car = findByIdOrThrow(id);
        var data = mapper.toDto(car);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<CarResponse>> findAll() {
        var cars = repository.findAll();
        if (cars.isEmpty()) createNotFoundResponse(NOT_FOUND);
        var data = cars.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<CarResponse> create(CarRequest request) {
        return null;
    }

    public ResponseItem<CarResponse> update(Long id, CarRequest request) {
        return null;
    }

    public void delete(Long id) {
        findByIdOrThrow(id);
        repository.softDeleteById(id);
    }

    public Car findEntityById(Long id) {
        return findByIdOrThrow(id);
    }

    public List<Car> findByModelId(Long id) {
        return repository.findByModelId(id);
    }

    /**
     * <b>Verilen id değerine göre araç nesnesini bulur.</b>
     * <p>
     * Araç bulunamazsa EntityNotFoundException fırlatır.
     *
     * @param id id değeri.
     * @return Araç nesnesi.
     * @throws EntityNotFoundException Araç bulunamazsa fırlatılır.
     */
    private Car findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }
}
