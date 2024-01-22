package com.kalayciburak.corepackage.advice;

import com.kalayciburak.corepackage.model.error.BaseError;
import com.kalayciburak.corepackage.model.response.ResponseItem;
import com.kalayciburak.corepackage.util.constant.ExceptionCodes;
import com.kalayciburak.corepackage.util.constant.Types;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class BaseRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception ex) {
        return buildResponseEntity(new BaseError(ex));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponseEntity(new BaseError(NOT_FOUND, Types.Exception.NOT_FOUND, ExceptionCodes.RESOURCE_NOT_FOUND,
                "Aranan kayıt bulunamadı.", ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new BaseError(CONFLICT, Types.Exception.DATA_INTEGRITY,
                    ExceptionCodes.DATA_INTEGRITY_VIOLATION, "Veri bütünlüğü korunamadı.", ex.getCause()));
        }
        return buildResponseEntity(new BaseError(ex));
    }

    private ResponseEntity<Object> buildResponseEntity(BaseError error) {
        Map<String, Object> errorData = new LinkedHashMap<>();
        errorData.put("className", error.getClazz());
        errorData.put("methodName", error.getMethod());
        errorData.put("lineNumber", error.getLine());
        errorData.put("debugMessage", error.getDebugMessage());
        errorData.put("childErrors", error.getChildErrors());

        ResponseItem<Object> responseItem = new ResponseItem<>(error.getType(), error.getCode(), errorData,
                error.getMessage(), false, 0);

        return new ResponseEntity<>(responseItem, error.getStatus());
    }
}