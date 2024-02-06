package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.basic.CityResponse;
import com.kalayciburak.inventoryservice.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cities", produces = "application/json")
public class CitiesController {
    private final CityService service;

    @GetMapping("/{id}")
    @Operation(summary = "Belirli bir ID'ye göre şehir getirir",
            description = "Verilen ID'ye sahip spesifik bir şehri getirir.")
    public ResponseItem<CityResponse> findById(
            @PathVariable @Parameter(description = "Şehir ID") Long id) {
        return service.findById(id);
    }

    @GetMapping
    @Operation(summary = "Tüm aktif şehirleri getirir",
            description = "Sistemdeki tüm aktif şehirlerin bir listesini getirir.")
    public ResponseItem<List<CityResponse>> findAll() {
        return service.findAll();
    }
}
