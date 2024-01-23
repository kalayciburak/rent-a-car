package com.kalayciburak.inventoryservice.api.controller;

import com.kalayciburak.corepackage.model.response.ResponseItem;
import com.kalayciburak.inventoryservice.model.dto.response.CarResponse;
import com.kalayciburak.inventoryservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarsController {
    private final CarService service;

    @GetMapping
    public ResponseItem<List<CarResponse>> findAll() {
        return service.findAll();
    }
}
