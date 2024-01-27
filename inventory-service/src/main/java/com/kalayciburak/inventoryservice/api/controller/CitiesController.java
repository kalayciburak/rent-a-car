package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.basic.CityResponse;
import com.kalayciburak.inventoryservice.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CitiesController {
    private final CityService service;

    @GetMapping("/{id}")
    public ResponseItem<CityResponse> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseItem<List<CityResponse>> findAll() {
        return service.findAll();
    }
}
