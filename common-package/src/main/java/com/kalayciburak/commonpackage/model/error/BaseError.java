package com.kalayciburak.commonpackage.model.error;

import com.kalayciburak.commonpackage.util.constant.ExceptionCodes;
import com.kalayciburak.commonpackage.util.constant.Paths;
import com.kalayciburak.commonpackage.util.constant.Types;
import jakarta.validation.ConstraintViolation;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class BaseError {
    private HttpStatus status;
    private String type;
    private String code;
    private String message;
    private String debugMessage;
    private String clazz;
    private String method;
    private int line;
    private List<ReferenceError> childErrors;

    public BaseError(HttpStatus status) {
        this.status = status;
    }

    public BaseError(Throwable cause) {
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
        this.type = Types.Exception.DEFAULT;
        this.code = ExceptionCodes.UNEXPECTED;
        this.message = "Beklenmeyen bir hata meydana geldi.";
        this.debugMessage = cause.getLocalizedMessage();
        this.clazz = cause.getStackTrace()[0].getClassName();
        this.method = cause.getStackTrace()[0].getMethodName();
        this.line = cause.getStackTrace()[0].getLineNumber();
        printStackTraceToConsole(cause);
    }

    public BaseError(HttpStatus status, String type, String code) {
        this.status = status;
        this.type = type;
        this.code = code;
    }

    public BaseError(HttpStatus status, String type, String code, String message, Throwable cause) {
        this.status = status;
        this.type = type;
        this.code = code;
        this.message = message;
        this.debugMessage = cause.getLocalizedMessage();
        this.clazz = cause.getStackTrace()[0].getClassName();
        this.method = cause.getStackTrace()[0].getMethodName();
        this.line = cause.getStackTrace()[0].getLineNumber();
        printStackTraceToConsole(cause);
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addChildError(ReferenceError childError) {
        if (childErrors == null) childErrors = new ArrayList<>();
        childErrors.add(childError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addChildError(new ValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addChildError(new ValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(
                cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(),
                cv.getInvalidValue(),
                cv.getMessage());
    }

    private void printStackTraceToConsole(Throwable throwable) {
        for (StackTraceElement element : throwable.getStackTrace()) {
            if (element.getClassName().startsWith(Paths.ConfigurationBasePackage)) {
                addChildError(new StackTraceError(element));
            }
        }
    }
}