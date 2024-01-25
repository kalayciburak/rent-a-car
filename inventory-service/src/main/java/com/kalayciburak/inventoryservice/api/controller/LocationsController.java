package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.LocationRequest;
import com.kalayciburak.inventoryservice.model.dto.response.LocationResponse;
import com.kalayciburak.inventoryservice.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locations")
public class LocationsController {
    private final LocationService service;

    @GetMapping("/id")
    public ResponseItem<LocationResponse> findById(Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseItem<List<LocationResponse>> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseItem<LocationResponse> create(@RequestBody LocationRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ResponseItem<LocationResponse> update(@PathVariable Long id, @RequestBody LocationRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
