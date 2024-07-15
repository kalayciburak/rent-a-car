package com.kalayciburak.filterservice.model.dto.response;

import java.math.BigDecimal;

public record CarResponse(
        String id,
        Long carId,
        Long modelId,
        Long brandId,
        Long cityId,
        Long locationId,
        Long carStatusId,
        Long fuelId,
        Long transmissionId,
        Long colorId,
        Integer year,
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
