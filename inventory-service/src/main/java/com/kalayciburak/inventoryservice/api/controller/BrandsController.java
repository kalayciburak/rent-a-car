package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.corepackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.BrandResponse;
import com.kalayciburak.inventoryservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping()
    public ResponseItem<List<BrandResponse>> findAll() {
        return service.findAll();
    }
}
