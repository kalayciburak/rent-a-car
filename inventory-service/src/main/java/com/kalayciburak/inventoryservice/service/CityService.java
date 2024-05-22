package com.kalayciburak.inventoryservice.service;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.advice.exception.CityNotFoundException;
import com.kalayciburak.inventoryservice.model.dto.response.basic.CityResponse;
import com.kalayciburak.inventoryservice.model.entity.City;
import com.kalayciburak.inventoryservice.repository.CityRepository;
import com.kalayciburak.inventoryservice.util.mapper.CityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kalayciburak.commonpackage.model.response.GenericResponse.createNotFoundResponse;
import static com.kalayciburak.commonpackage.model.response.GenericResponse.createSuccessResponse;
import static com.kalayciburak.commonpackage.util.constant.Messages.Inventory.City.*;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityMapper mapper;
    private final CityRepository repository;

    public ResponseItem<CityResponse> findById(Long id) {
        var city = findByIdOrThrow(id);
        var data = mapper.toDto(city);

        return createSuccessResponse(data, FOUND);
    }

    public ResponseItem<List<CityResponse>> findAll() {
        var cities = repository.findAll();
        if (cities.isEmpty()) return createNotFoundResponse(NOT_FOUND);
        var data = cities.stream().map(mapper::toDto).toList();

        return createSuccessResponse(data, LISTED);
    }

    /**
     * <b>Verilen id değerine göre şehir nesnesini bulur.</b>
     * <p>
     * Şehir bulunamazsa EntityNotFoundException fırlatır.
     *
     * @param id id değeri.
     * @return Şehir nesnesi.
     * @throws CityNotFoundException Şehir bulunamazsa fırlatılır.
     */
    public City findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() -> new CityNotFoundException(NOT_FOUND));
    }
}
