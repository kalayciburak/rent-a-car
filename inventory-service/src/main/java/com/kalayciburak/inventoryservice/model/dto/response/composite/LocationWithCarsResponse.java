package com.kalayciburak.inventoryservice.model.dto.response.composite;

import com.kalayciburak.inventoryservice.model.dto.response.basic.CarResponse;

import java.util.List;

public record LocationWithCarsResponse(Long id, String address, String cityName, List<CarResponse> cars) {}
