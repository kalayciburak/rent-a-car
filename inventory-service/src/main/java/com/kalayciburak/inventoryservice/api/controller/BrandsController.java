package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.BrandRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.BrandResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.BrandWithModelsResponse;
import com.kalayciburak.inventoryservice.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/brands", produces = "application/json")
public class BrandsController {
    private final BrandService service;

    @GetMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye göre marka getirir",
            description = "Verilen ID'ye sahip spesifik bir markayı getirir.")
    public ResponseItem<BrandResponse> findById(
            @PathVariable @Parameter(description = "Marka ID") Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/with-models")
    @Operation(summary = "ID'ye göre marka ve modellerini getirir",
            description = "Verilen ID'ye sahip markanın kendisi ile birlikte ilgili tüm modellerini getirir.")
    public ResponseItem<BrandWithModelsResponse> findByIdWithModels(
            @PathVariable @Parameter(description = "Marka ID") Long id) {
        return service.findByIdWithModels(id);
    }

    @GetMapping
    @Operation(summary = "Tüm aktif markaları getirir",
            description = "Sistemdeki tüm aktif markaların bir listesini getirir.")
    public ResponseItem<List<BrandResponse>> findAll() {
        return service.findAll();
    }

    @GetMapping("/with-models")
    @Operation(summary = "Tüm markaları ve modellerini getirir",
            description = "Sistemdeki tüm aktif markaların ve bu markalara ait tüm modellerin bir listesini getirir.")
    public ResponseItem<List<BrandWithModelsResponse>> findAllWithModels() {
        return service.findAllWithModels();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Yeni bir marka oluşturur",
            description = "Verilen marka bilgilerine göre yeni bir marka kaydı oluşturur.")
    public ResponseItem<BrandResponse> create(
            @RequestBody @Parameter(description = "Marka bilgisi") BrandRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mevcut bir markayı günceller",
            description = "Belirtilen ID'ye sahip markanın bilgilerini günceller.")
    public ResponseItem<BrandResponse> update(
            @PathVariable @Parameter(description = "Marka ID") Long id,
            @RequestBody @Parameter(description = "Yeni marka bilgisi") BrandRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Belirli bir ID'ye sahip markayı siler",
            description = "Verilen ID'ye sahip markayı sistemden siler.")
    public void delete(
            @PathVariable @Parameter(description = "Silinecek marka ID") Long id) {
        service.delete(id);
    }
}
