package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.LocationRequest;
import com.kalayciburak.inventoryservice.model.dto.response.LocationResponse;
import com.kalayciburak.inventoryservice.model.entity.Location;
import com.kalayciburak.inventoryservice.repository.LocationRepository;
import com.kalayciburak.inventoryservice.service.helper.CarHelperService;
import com.kalayciburak.inventoryservice.util.mapper.LocationMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.Location.*;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {
    private final LocationMapper mapper;
    private final CityService cityService;
    private final LocationRepository repository;
    private final CarHelperService carHelperService;

    public ResponseItem<LocationResponse> findById(Long id) {
        var location = findByIdOrThrow(id);
        var data = mapper.toDto(location);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<LocationResponse>> findAll() {
        var locations = repository.findAll();
        if (locations.isEmpty()) createNotFoundResponse(NOT_FOUND);
        var data = locations.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<LocationResponse> create(LocationRequest request) {
        cityService.findByIdOrThrow(request.cityId());
        var location = mapper.toEntity(request);
        var savedLocation = repository.save(location);
        var data = mapper.toDto(savedLocation);

        return createSuccessResponse(data, SAVED);
    }

    public ResponseItem<LocationResponse> update(Long id, LocationRequest request) {
        var location = findByIdOrThrow(id);
        mapper.updateEntity(request, location);
        var savedLocation = repository.save(location);
        var data = mapper.toDto(savedLocation);

        return createSuccessResponse(data, UPDATED);
    }

    public void delete(Long id) {
        var location = findByIdOrThrow(id);
        deleteAvailableCarsFromLocation(location);
        repository.softDeleteById(id);
    }

    /**
     * <b>Verilen id değerine göre lokasyon nesnesini bulur.</b>
     * <p>
     * Lokasyon bulunamazsa EntityNotFoundException fırlatır.
     *
     * @param id id değeri.
     * @return Lokasyon nesnesi.
     * @throws EntityNotFoundException Lokasyon bulunamazsa fırlatılır.
     */
    private Location findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(NOT_FOUND));
    }

    /**
     * <b>Location nesnesini kontrol eder ve içerdiği araçları denetler ve siler.</b>
     *
     * @param location Location nesnesi, içerdiği araçları kontrol etmek ve silmek için kullanılır.
     */
    private void deleteAvailableCarsFromLocation(Location location) {
        Optional.ofNullable(location.getCars())
                .ifPresent(cars -> cars.forEach(carHelperService::validateAvailabilityAndDeleteCar));
    }
}
