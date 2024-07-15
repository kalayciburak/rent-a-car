package com.kalayciburak.commonpackage.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kalayciburak.commonpackage.model.error.BaseError;
import com.kalayciburak.commonpackage.model.response.ResponseItem;
import com.kalayciburak.commonpackage.util.constant.ExceptionCodes;
import com.kalayciburak.commonpackage.util.constant.Profiles;
import com.kalayciburak.commonpackage.util.constant.Types;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class BaseRestExceptionHandler {
    @Value("${spring.profiles.active:default}")
    private String activeProfile;

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception ex) {
        return buildResponseEntity(new BaseError(ex));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleRuntime(RuntimeException ex) {
        return buildResponseEntity(new BaseError(ex));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.VALIDATION, ExceptionCodes.METHOD_ARGUMENT_NOT_VALID);
        baseError.setMessage("Girdiğiniz bilgilerde hata var. Lütfen kontrol edip tekrar deneyiniz.");
        baseError.addValidationErrors(ex.getBindingResult().getFieldErrors());
        baseError.addValidationError(ex.getBindingResult().getGlobalErrors());

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(jakarta.validation.ConstraintViolationException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.VALIDATION, ExceptionCodes.METHOD_ARGUMENT_NOT_VALID);
        baseError.setMessage("Girdiğiniz bilgilerde hata var. Lütfen kontrol edip tekrar deneyiniz.");
        baseError.addValidationErrors(ex.getConstraintViolations());

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.TYPE_MISMATCH, ExceptionCodes.METHOD_ARGUMENT_TYPE_MISMATCH);

        var message = String.format("'%s' parametresinin değeri '%s', '%s' tipine dönüştürülemedi. Lütfen doğru bilgileri giriniz.",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        baseError.setMessage(message);
        baseError.setDebugMessage(ex.getMessage());

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        var message = ex.getParameterName() + " parametresi eksik. Lütfen gerekli bilgileri sağlayınız.";

        return buildResponseEntity(new BaseError(BAD_REQUEST, Types.Exception.REQUEST_PARAMETER, ExceptionCodes.SERVLET_REQUEST_PARAMETER, message, ex));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleUnSupportedMediaTypeException(HttpMediaTypeNotSupportedException ex) {
        var builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" medya tipi desteklenmiyor. Desteklenen medya tipleri: ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        var message = builder.substring(0, builder.length() - 2);

        return buildResponseEntity(new BaseError(UNSUPPORTED_MEDIA_TYPE, Types.Exception.MEDIA_TYPE, ExceptionCodes.UNSUPPORTED_MEDIA_TYPE, message, ex));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex) {
        var message = "JSON çıktısı yazılırken hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.";

        return buildResponseEntity(new BaseError(INTERNAL_SERVER_ERROR, Types.Exception.WRITING_OUTPUT, ExceptionCodes.MESSAGE_NOT_WRITABLE, message, ex));
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.HANDLER, ExceptionCodes.NO_HANDLER_FOUND);
        baseError.setMessage(String.format("İstek yapılan kaynak bulunamadı. Lütfen %s URL'sini ve %s metodunu kontrol edip tekrar deneyiniz.", ex.getRequestURL(), ex.getHttpMethod()));
        baseError.setDebugMessage(ex.getMessage());

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleFeignException(FeignException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.FEIGN, ExceptionCodes.FEIGN);
        baseError.setMessage("Hizmet çağrısında bir hata meydana geldi. Lütfen daha sonra tekrar deneyiniz.");

        Optional.ofNullable(ex.getStackTrace())
                .filter(stackTrace -> stackTrace.length > 0)
                .ifPresent(stackTrace -> {
                    baseError.setClazz(stackTrace[0].getClassName());
                    baseError.setMethod(stackTrace[0].getMethodName());
                    baseError.setLine(stackTrace[0].getLineNumber());
                });

        baseError.setDebugMessage(
                Optional.ofNullable(ex.getCause())
                        .map(Throwable::getCause)
                        .map(Throwable::getLocalizedMessage)
                        .orElse(ex.getLocalizedMessage())
        );

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponseEntity(new BaseError(NOT_FOUND, Types.Exception.NOT_FOUND, ExceptionCodes.RESOURCE_NOT_FOUND, "Aradığınız kayıt bulunamadı. Lütfen doğru bilgileri girdiğinizden emin olun.", ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        var message = "Veri bütünlüğü hatası. Lütfen daha sonra tekrar deneyiniz.";
        if (ex.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(new BaseError(CONFLICT, Types.Exception.DATA_INTEGRITY, ExceptionCodes.DATA_INTEGRITY_VIOLATION, message, ex.getCause()));
        }

        return buildResponseEntity(new BaseError(ex));
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.JSON_PROCESSING, ExceptionCodes.JSON_PROCESSING_EXCEPTION, "JSON işleme hatası meydana geldi. Lütfen daha sonra tekrar deneyiniz.", ex);

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler(FeignException.Unauthorized.class)
    public ResponseEntity<Object> handleFeignUnauthorizedException(FeignException.Unauthorized ex) {
        var baseError = new BaseError(UNAUTHORIZED, Types.Exception.UNAUTHORIZED, ExceptionCodes.UNAUTHORIZED, "Yetkilendirme hatası. Lütfen kimlik bilgilerinizi kontrol edin.", ex);

        return buildResponseEntity(baseError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        var baseError = new BaseError(BAD_REQUEST, Types.Exception.ILLEGAL_ARGUMENT, ExceptionCodes.ILLEGAL_ARGUMENT, "Geçersiz argüman. Lütfen doğru bilgileri giriniz.", ex);

        return buildResponseEntity(baseError);
    }

    private ResponseEntity<Object> buildResponseEntity(BaseError error) {
        Map<String, Object> errorData = new LinkedHashMap<>();

        // ? Geliştirme ortamında çalışırken hata detaylarını göster.
        if (!isProdLikeProfile()) {
            errorData.put("className", error.getClazz());
            errorData.put("methodName", error.getMethod());
            errorData.put("lineNumber", error.getLine());
            errorData.put("debugMessage", error.getDebugMessage());
            errorData.put("childErrors", error.getChildErrors());
        }

        ResponseItem<Object> responseItem = new ResponseItem<>(
                error.getType(),
                error.getCode(),
                errorData,
                error.getMessage(),
                false,
                0
        );

        return new ResponseEntity<>(responseItem, error.getStatus());
    }

    /**
     * <b>Aktif profilin prod benzeri bir profil olup olmadığını kontrol eder.</b>
     *
     * @return Aktif profil 'prod', 'preprod' veya 'production' ise <code>true</code>, değilse <code>false</code> döner.
     */
    private boolean isProdLikeProfile() {
        return Profiles.PRODUCTION.contains(activeProfile);
    }
}