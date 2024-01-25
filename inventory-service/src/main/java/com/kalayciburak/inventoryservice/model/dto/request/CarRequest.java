package com.kalayciburak.inventoryservice.model.dto.request;

import com.kalayciburak.commonpackage.util.constant.Regex;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CarRequest(
        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Model seçimi yapınız.")
        Long modelId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Lokasyon seçimi yapınız.")
        Long locationId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Yakıt türü seçimi yapınız.")
        Long fuelTypeId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Vites türü seçimi yapınız.")
        Long transmissionTypeId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Renk türü seçimi yapınız.")
        Long colorTypeId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Araç durumu seçimi yapınız.")
        Long carStatusId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        int year,

        @Pattern(regexp = Regex.Plate)
        @NotNull(message = "Bu alan boş bırakılamaz.")
        String plate,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 100, message = "Günlük fiyat 100 TL'den az olamaz.")
        double dailyPrice
) {}
