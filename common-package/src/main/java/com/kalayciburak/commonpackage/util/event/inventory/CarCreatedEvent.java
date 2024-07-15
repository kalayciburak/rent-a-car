package com.kalayciburak.commonpackage.util.event.inventory;

import com.kalayciburak.commonpackage.util.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CarCreatedEvent implements BaseEvent {
    private Long carId;
    private Long modelId;
    private Long brandId;
    private Long cityId;
    private Long locationId;
    private Long carStatusId;
    private Long fuelId;
    private Long transmissionId;
    private Long colorId;
    private Integer year;
    private BigDecimal dailyPrice;
    private String plate;
    private String color;
    private String fuel;
    private String transmission;
    private String carStatus;
    private String modelName;
    private String brandName;
    private String cityName;
    private String address;
}
