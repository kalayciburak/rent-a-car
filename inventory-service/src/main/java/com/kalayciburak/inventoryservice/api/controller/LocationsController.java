package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.LocationRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.LocationResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.LocationWithCarsResponse;
import com.kalayciburak.inventoryservice.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/locations", produces = "application/json")
public class LocationsController {
    private final LocationService service;

    @GetMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye göre konum getirir",
            description = "Verilen ID'ye sahip spesifik bir konumu getirir.")
    public ResponseItem<LocationResponse> findById(
            @PathVariable @Parameter(description = "Konum ID") Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/with-cars")
    @Operation(summary = "ID'ye göre konum ve araçlarını getirir",
            description = "Verilen ID'ye sahip konumun kendisi ile birlikte ilgili tüm araçlarını getirir.")
    public ResponseItem<LocationWithCarsResponse> findByIdWithCars(
            @PathVariable @Parameter(description = "Konum ID") Long id) {
        return service.findByIdWithCars(id);
    }

    @GetMapping
    @Operation(summary = "Tüm aktif konumları getirir",
            description = "Sistemdeki tüm aktif konumların bir listesini getirir.")
    public ResponseItem<List<LocationResponse>> findAll() {
        return service.findAll();
    }

    @GetMapping("/with-cars")
    @Operation(summary = "Tüm konumları ve araçlarını getirir",
            description = "Sistemdeki tüm aktif konumların ve bu konumlara ait tüm araçların bir listesini getirir.")
    public ResponseItem<List<LocationWithCarsResponse>> findAllWithCars() {
        return service.findAllWithCars();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Yeni bir konum oluşturur",
            description = "Verilen konum bilgilerine göre yeni bir konum kaydı oluşturur.")
    public ResponseItem<LocationResponse> create(
            @RequestBody @Parameter(description = "Konum bilgisi") LocationRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mevcut bir konumu günceller",
            description = "Belirtilen ID'ye sahip konumun bilgilerini günceller.")
    public ResponseItem<LocationResponse> update(
            @PathVariable @Parameter(description = "Konum ID") Long id,
            @RequestBody @Parameter(description = "Yeni konum bilgisi") LocationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Belirli bir ID'ye sahip konumu siler",
            description = "Verilen ID'ye sahip konumu sistemden siler.")
    public void delete(
            @PathVariable @Parameter(description = "Silinecek konum ID") Long id) {
        service.delete(id);
    }
}
