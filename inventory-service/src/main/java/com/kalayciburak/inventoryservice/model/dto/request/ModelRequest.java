package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ModelRequest(
        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Min(value = 1, message = "Model seçimi yapınız.")
        Long brandId,
        String name
) {}
