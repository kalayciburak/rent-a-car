package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.ModelRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.ModelResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.ModelWithCarsResponse;
import com.kalayciburak.inventoryservice.service.ModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/models", produces = "application/json")
public class ModelsController {
    private final ModelService service;

    @GetMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye göre model getirir",
            description = "Verilen ID'ye sahip spesifik bir modeli getirir.")
    public ResponseItem<ModelResponse> findById(
            @PathVariable @Parameter(description = "Model ID") Long id) {
        return service.findById(id);
    }

    @GetMapping("/{id}/with-cars")
    @Operation(summary = "ID'ye göre model ve araçlarını getirir",
            description = "Verilen ID'ye sahip modelin kendisi ile birlikte ilgili tüm araçlarını getirir.")
    public ResponseItem<ModelWithCarsResponse> findByIdWithCars(
            @PathVariable @Parameter(description = "Model ID") Long id) {
        return service.findByIdWithCars(id);
    }

    @GetMapping
    @Operation(summary = "Tüm aktif modelleri getirir",
            description = "Sistemdeki tüm aktif modellerin bir listesini getirir.")
    public ResponseItem<List<ModelResponse>> findAll() {
        return service.findAll();
    }

    @GetMapping("/with-cars")
    @Operation(summary = "Tüm modelleri ve araçlarını getirir",
            description = "Sistemdeki tüm aktif modellerin ve bu modellere ait tüm araçların bir listesini getirir.")
    public ResponseItem<List<ModelWithCarsResponse>> findAllWithCars() {
        return service.findAllWithCars();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Yeni bir model oluşturur",
            description = "Verilen model bilgilerine göre yeni bir model kaydı oluşturur.")
    public ResponseItem<ModelResponse> create(
            @RequestBody @Parameter(description = "Model bilgisi") ModelRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mevcut bir modeli günceller",
            description = "Belirtilen ID'ye sahip modelin bilgilerini günceller.")
    public ResponseItem<ModelResponse> update(
            @PathVariable @Parameter(description = "Model ID") Long id,
            @RequestBody @Parameter(description = "Yeni model bilgisi") ModelRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Belirli bir ID'ye sahip modeli siler",
            description = "Verilen ID'ye sahip modeli sistemden siler.")
    public void delete(
            @PathVariable @Parameter(description = "Silinecek model ID") Long id) {
        service.delete(id);
    }
}
