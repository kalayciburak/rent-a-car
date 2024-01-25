package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.CarRequest;
import com.kalayciburak.inventoryservice.model.dto.response.CarResponse;
import com.kalayciburak.inventoryservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarsController {
    private final CarService service;

    @GetMapping("/id")
    public ResponseItem<CarResponse> findById(Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseItem<List<CarResponse>> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseItem<CarResponse> create(@RequestBody CarRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ResponseItem<CarResponse> update(@PathVariable Long id, @RequestBody CarRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
