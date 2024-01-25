package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LocationRequest(
        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Şehir seçimi yapınız.")
        Long cityId,

        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Size(min = 15, max = 255, message = "Bu alan en az 15, en fazla 255 karakter olabilir.")
        String address
) {}
