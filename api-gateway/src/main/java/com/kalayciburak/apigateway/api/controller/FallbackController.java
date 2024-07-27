package com.kalayciburak.apigateway.api.controller;

import com.kalayciburak.apigateway.model.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class FallbackController {
    @RequestMapping(value = "/fallback", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ErrorResponse> fallback() {
        var errorResponse = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                LocalDateTime.now(),
                "Service is temporarily unavailable. Please try again later."
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }
}