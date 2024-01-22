package com.kalayciburak.corepackage.advice.exception;

import com.kalayciburak.corepackage.util.constant.Types;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
public class CustomRuntimeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final HttpStatus status;
    private final String type;
    private final String code;

    public CustomRuntimeException(String code, String message) {
        this(BAD_REQUEST, Types.Exception.RUNTIME, code, message);
    }

    public CustomRuntimeException(HttpStatus status, String type, String code, String message) {
        super(message);
        this.type = type;
        this.code = code;
        this.status = status;
    }
}