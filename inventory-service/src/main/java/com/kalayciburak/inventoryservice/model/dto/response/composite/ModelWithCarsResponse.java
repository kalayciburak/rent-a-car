package com.kalayciburak.inventoryservice.model.dto.response.composite;

import com.kalayciburak.inventoryservice.model.dto.response.basic.CarResponse;

import java.util.List;

public record ModelWithCarsResponse(Long id, String name, List<CarResponse> cars) {}
