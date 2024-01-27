package com.kalayciburak.inventoryservice.model.dto.response.basic;

import java.math.BigDecimal;

public record CarResponse(
        Long id,
        int year,
        BigDecimal dailyPrice,
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
