package com.kalayciburak.apigateway.model.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        LocalDateTime timestamp,
        String message) {
}
