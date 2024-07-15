package com.kalayciburak.filterservice.api.controller;

import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.filterservice.model.dto.response.CarResponse;
import com.kalayciburak.filterservice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cars", produces = "application/json")
public class CarsController {
    private final CarService service;

    @GetMapping
    public ResponseItem<List<CarResponse>> findAll() {
        return service.findAll();
    }
}
