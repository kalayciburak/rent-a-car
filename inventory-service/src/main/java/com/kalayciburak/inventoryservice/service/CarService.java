package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.broker.kafka.producer.BaseProducer;
import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.commonpackage.util.event.inventory.CarDeletedEvent;
import com.kalayciburak.inventoryservice.advice.exception.car.CarNotFoundException;
import com.kalayciburak.inventoryservice.advice.exception.car.PlateExistsException;
import com.kalayciburak.inventoryservice.model.dto.request.CarRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.CarResponse;
import com.kalayciburak.inventoryservice.model.entity.Car;
import com.kalayciburak.inventoryservice.repository.CarRepository;
import com.kalayciburak.inventoryservice.service.helper.ModelHelperService;
import com.kalayciburak.inventoryservice.util.mapper.CarMapper;
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
    private final BaseProducer producer;
    private final CarRepository repository;
    private final LookupService lookupService;
    private final LocationService locationService;
    private final ModelHelperService modelService;

    public ResponseItem<CarResponse> findById(Long id) {
        var car = findByIdOrThrow(id);
        var data = mapper.toDto(car);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<CarResponse>> findAll() {
        var cars = repository.findAll();
        if (cars.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = cars.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<List<CarResponse>> findByStatus(String status) {
        var cars = repository.findByStatus(status);
        if (cars.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = cars.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, LISTED);
    }

    public ResponseItem<CarResponse> create(CarRequest request) {
        validateLookupIds(request);
        validatePlate(request.plate());
        var car = mapper.toEntity(request);
        updateCarDetails(car);
        setCarStatusToAvailable(car);
        var savedCar = repository.save(car);
        var data = mapper.toDto(savedCar);
        produceCarCreatedEvent(data);

        return createSuccessResponse(data, SAVED);
    }

    public ResponseItem<CarResponse> update(Long id, CarRequest request) {
        var car = findByIdOrThrow(id);
        if (!car.getPlate().equals(request.plate())) validatePlate(request.plate());
        validateLookupIds(request);
        mapper.updateEntity(request, car);
        updateCarDetails(car);
        var savedCar = repository.save(car);
        var data = mapper.toDto(savedCar);
        produceCarUpdatedEvent(data);

        return createSuccessResponse(data, UPDATED);
    }

    public void delete(Long id) {
        findByIdOrThrow(id);
        repository.softDeleteById(id);
        produceCarDeletedEvent(id);
    }

    /**
     * <b>Verilen plaka değerine göre aracın mevcut olup olmadığını kontrol eder.</b>
     * <p>
     * Bu metod, plaka değerine göre aracın mevcut olup olmadığını kontrol eder.
     * Eğer araç mevcutsa {@link PlateExistsException} fırlatır.
     *
     * @param plate plaka değeri.
     * @throws PlateExistsException Plaka mevcutsa fırlatılır.
     */
    private void validatePlate(String plate) {
        if (repository.existsByPlate(plate)) throw new PlateExistsException(PLATE_EXISTS);
    }

    /**
     * <b>Verilen lookup id'lerinin geçerli olup olmadığını kontrol eder.</b>
     *
     * @param request araç isteği.
     */
    private void validateLookupIds(CarRequest request) {
        lookupService.validateFuelTypeId(request.fuelTypeId());
        lookupService.validateColorTypeId(request.colorTypeId());
        lookupService.validateTransmissionTypeId(request.transmissionTypeId());
    }

    /**
     * <b>Verilen aracın detaylarını günceller</b>
     * <p>
     * Bu method, aracın model, yakıt türü, konum, renk türü ve vites türü gibi özelliklerini günceller.
     * Her bir özellik, ilgili hizmetler kullanılarak güncellenir.
     *
     * @param car Güncellenecek araç nesnesi. Bu nesnenin model, yakıt türü, konum, renk türü,
     *            vites türü ve durum özellikleri güncellenir.
     */
    private void updateCarDetails(Car car) {
        car.setModel(modelService.findEntityById(car.getModel().getId()));
        car.setFuelType(lookupService.findEntityById(car.getFuelType().getId()));
        car.setLocation(locationService.findEntityById(car.getLocation().getId()));
        car.setColorType(lookupService.findEntityById(car.getColorType().getId()));
        car.setTransmissionType(lookupService.findEntityById(car.getTransmissionType().getId()));
    }

    /**
     * <b>Verilen aracın durumunu "Müsait" olarak set eder.</b>
     *
     * @param car Durumu güncellenecek araç.
     */
    private void setCarStatusToAvailable(Car car) {
        car.setCarStatus(lookupService.findEntityById(lookupService.getAvailableId()));
    }

    /**
     * <b>Verilen id değerine göre araç nesnesini bulur.</b>
     * <p>
     * Araç bulunamazsa EntityNotFoundException fırlatır.
     *
     * @param id id değeri.
     * @return Araç nesnesi.
     * @throws CarNotFoundException Araç bulunamazsa fırlatılır.
     */
    private Car findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new CarNotFoundException(NOT_FOUND));
    }

    private void produceCarCreatedEvent(CarResponse response) {
        var event = mapper.toCreatedEvent(response);
        producer.sendMessage(event, "car.created");
    }

    private void produceCarUpdatedEvent(CarResponse response) {
        var event = mapper.toUpdatedEvent(response);
        producer.sendMessage(event, "car.updated");
    }

    private void produceCarDeletedEvent(Long carId) {
        var event = new CarDeletedEvent(carId);
        producer.sendMessage(event, "car.deleted");
    }
}
