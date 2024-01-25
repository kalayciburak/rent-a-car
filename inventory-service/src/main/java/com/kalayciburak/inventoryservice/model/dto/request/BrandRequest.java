package com.kalayciburak.inventoryservice.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BrandRequest(
        @NotNull(message = "Bu alan boş bırakılamaz.")
        @Size(min = 2, max = 35, message = "Marka adı 2-35 karakter arasında olmalıdır.")
        String name
) {}
