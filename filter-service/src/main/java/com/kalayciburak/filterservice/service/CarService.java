package com.kalayciburak.filterservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.commonpackage.util.event.inventory.*;
import com.kalayciburak.filterservice.model.dto.response.CarResponse;
import com.kalayciburak.filterservice.repository.CarRepository;
import com.kalayciburak.filterservice.util.mapper.CarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Car.LISTED;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Car.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarMapper mapper;
    private final CarRepository repository;

    public ResponseItem<List<CarResponse>> findAll() {
        var cars = repository.findAll();
        var response = cars.stream().map(mapper::toDto).toList();
        if (response.isEmpty()) return createNotFoundResponse(NOT_FOUND);

        return createSuccessResponse(response, LISTED);
    }

    public ResponseItem<CarResponse> findByCarId(Long carId) {
        var car = repository.findByCarId(carId);
        if (car.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var response = mapper.toDto(car.get());

        return createSuccessResponse(response, LISTED);
    }

    /**
     * <b>Bu metot, CarCreatedEvent türündeki olayları tüketir ve araçları kaydeder.</b>
     *
     * @param event Oluşturulan araç nesnesi
     */
    public void onCarCreated(CarCreatedEvent event) {
        var car = mapper.toEntity(event);
        repository.save(car);
    }

    /**
     * <b>Bu metot, CarUpdatedEvent türündeki olayları tüketir ve araçları günceller.</b>
     *
     * @param event Güncellenen araç nesnesi
     */
    public void onCarUpdated(CarUpdatedEvent event) {
        var car = repository.findByCarId(event.getCarId());
        if (car.isEmpty()) return;
        var entity = car.get();
        mapper.updateEntity(event, entity);
        repository.save(entity);
    }

    /**
     * <b>Bu metot, CarDeletedEvent türündeki olayları tüketir ve araçları siler.</b>
     *
     * @param event Silinen araç nesnesi
     */
    public void onCarDeleted(CarDeletedEvent event) {
        repository.deleteByCarId(event.getId());
    }

    /**
     * <b>Bu metot, BrandUpdatedEvent türündeki olayları tüketir ve marka adlarını günceller.</b>
     *
     * @param event Güncellenen marka nesnesi
     */
    public void onBrandUpdated(BrandUpdatedEvent event) {
        var cars = repository.findAllByBrandId(event.getId());
        cars.forEach(car -> {
            car.setBrandName(event.getName());
            repository.save(car);
        });
    }

    /**
     * <b>Bu metot, BrandDeletedEvent türündeki olayları tüketir ve marka nesnelerini siler.</b>
     *
     * @param event Silinen marka nesnesi
     */
    public void onBrandDeleted(BrandDeletedEvent event) {
        repository.deleteByBrandId(event.getId());
    }
}
