package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.ModelRequest;
import com.kalayciburak.inventoryservice.model.dto.response.basic.ModelResponse;
import com.kalayciburak.inventoryservice.model.dto.response.composite.ModelWithCarsResponse;
import com.kalayciburak.inventoryservice.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping("/id")
    public ResponseItem<ModelResponse> findById(Long id) {
        return service.findById(id);
    }

    @GetMapping("/id/with-cars")
    public ResponseItem<ModelWithCarsResponse> findByIdWithCars(Long id) {
        return service.findByIdWithCars(id);
    }

    @GetMapping
    public ResponseItem<List<ModelResponse>> findAll() {
        return service.findAll();
    }

    @GetMapping("/with-cars")
    public ResponseItem<List<ModelWithCarsResponse>> findAllWithCars() {
        return service.findAllWithCars();
    }

    @PostMapping
    public ResponseItem<ModelResponse> create(@RequestBody ModelRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ResponseItem<ModelResponse> update(@PathVariable Long id, @RequestBody ModelRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
