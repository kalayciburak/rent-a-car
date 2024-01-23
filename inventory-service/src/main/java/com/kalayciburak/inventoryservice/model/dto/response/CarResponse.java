package com.kalayciburak.inventoryservice.model.dto.response;

public record CarResponse(
        Long id,
        int year,
        String plate,
        String color,
        String fuel,
        String transmission,
        String carStatus,
        String modelName,
        String brandName,
        String cityName,
        String address
) {}
