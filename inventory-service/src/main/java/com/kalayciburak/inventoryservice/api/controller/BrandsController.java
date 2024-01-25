package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.request.BrandRequest;
import com.kalayciburak.inventoryservice.model.dto.response.BrandResponse;
import com.kalayciburak.inventoryservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandsController {
    private final BrandService service;

    @GetMapping("/id")
    public ResponseItem<BrandResponse> findById(Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseItem<List<BrandResponse>> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseItem<BrandResponse> create(@RequestBody BrandRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ResponseItem<BrandResponse> update(@PathVariable Long id, @RequestBody BrandRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
