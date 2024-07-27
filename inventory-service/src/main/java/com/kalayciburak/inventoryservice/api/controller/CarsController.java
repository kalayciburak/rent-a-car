package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.CarRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.CarResponse;
import com.kalayciburak.inventoryservice.model.enums.CarStatus;
import com.kalayciburak.inventoryservice.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars", produces = "application/json")
public class CarsController {
    private final CarService service;

    @GetMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye göre araç getirir",
            description = "Verilen ID'ye sahip spesifik bir aracı getirir.")
    public ResponseItem<CarResponse> findById(
            @PathVariable @Parameter(description = "Araç ID") Long id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Tüm aktif araçları getirir",
            description = "Sistemdeki tüm aktif araçların bir listesini getirir.")
    public ResponseItem<List<CarResponse>> findAll() {
        return service.findAll();
    }

    @GetMapping("/status")
    @Operation(summary = "Araç durumuna göre araçları getirir",
            description = "Verilen araç durumuna göre araçları getirir.")
    public ResponseItem<List<CarResponse>> findByStatus(
            @RequestParam @Parameter(description = "Araç durumu") CarStatus status) {
        return service.findByStatus(status.getStatus());
    }

    @PostMapping
    @Operation(summary = "Yeni bir araç oluşturur",
            description = "Verilen araç bilgilerine göre yeni bir araç kaydı oluşturur.")
    public ResponseItem<CarResponse> create(
            @RequestBody @Parameter(description = "Araç bilgisi") CarRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mevcut bir aracı günceller",
            description = "Belirtilen ID'ye sahip aracın bilgilerini günceller.")
    public ResponseItem<CarResponse> update(
            @PathVariable @Parameter(description = "Araç ID") Long id,
            @RequestBody @Parameter(description = "Yeni araç bilgisi") CarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye sahip aracı siler",
            description = "Verilen ID'ye sahip aracı sistemden siler.")
    public void delete(
            @PathVariable @Parameter(description = "Silinecek araç ID") Long id) {
        service.delete(id);
    }
}
