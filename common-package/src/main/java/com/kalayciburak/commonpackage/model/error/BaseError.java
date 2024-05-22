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

/**
 * BaseError sınıfı, hata bilgilerini tutmak için kullanılır.
 */
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

    /**
     * HTTP durumu ile bir BaseError nesnesi oluşturur.
     *
     * @param status HTTP durumu
     */
    public BaseError(HttpStatus status) {
        this.status = status;
    }

    /**
     * Beklenmeyen bir hata durumunda bir BaseError nesnesi oluşturur.
     *
     * @param cause Oluşan hata
     */
    public BaseError(Throwable cause) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, Types.Exception.DEFAULT, ExceptionCodes.UNEXPECTED, "Beklenmeyen bir hata meydana geldi.", cause);
    }

    /**
     * HTTP durumu, hata tipi ve hata kodu ile bir BaseError nesnesi oluşturur.
     *
     * @param status HTTP durumu
     * @param type   Hata tipi
     * @param code   Hata kodu
     */
    public BaseError(HttpStatus status, String type, String code) {
        this.status = status;
        this.type = type;
        this.code = code;
    }

    /**
     * Tüm hata bilgileri ile bir BaseError nesnesi oluşturur.
     *
     * @param status  HTTP durumu
     * @param type    Hata tipi
     * @param code    Hata kodu
     * @param message Hata mesajı
     * @param cause   Oluşan hata
     */
    public BaseError(HttpStatus status, String type, String code, String message, Throwable cause) {
        this.status = status;
        this.type = type;
        this.code = code;
        this.message = message;
        setDebugInfo(cause);
    }

    /**
     * Global hata nesnelerini ekler.
     *
     * @param globalErrors Global hata nesneleri listesi
     */
    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    /**
     * Kısıtlama ihlallerini ekler.
     *
     * @param constraintViolations Kısıtlama ihlalleri kümesi
     */
    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    /**
     * Alan hatalarını ekler.
     *
     * @param fieldErrors Alan hataları listesi
     */
    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    /**
     * Çocuk hatayı ekler.
     *
     * @param childError Eklenmek istenen çocuk hata
     */
    private void addChildError(ReferenceError childError) {
        if (childErrors == null) childErrors = new ArrayList<>();
        childErrors.add(childError);
    }

    /**
     * Validasyon hatası ekler.
     *
     * @param object        Hata nesnesi
     * @param field         Hata alanı
     * @param rejectedValue Reddedilen değer
     * @param message       Hata mesajı
     */
    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addChildError(new ValidationError(object, field, rejectedValue, message));
    }

    /**
     * Validasyon hatası ekler.
     *
     * @param object  Hata nesnesi
     * @param message Hata mesajı
     */
    private void addValidationError(String object, String message) {
        addChildError(new ValidationError(object, message));
    }

    /**
     * Alan hatasını ekler.
     *
     * @param fieldError Alan hatası nesnesi
     */
    private void addValidationError(FieldError fieldError) {
        addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    /**
     * Nesne hatasını ekler.
     *
     * @param objectError Nesne hatası
     */
    private void addValidationError(ObjectError objectError) {
        addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    /**
     * Kısıtlama ihlalini ekler.
     *
     * @param cv Kısıtlama ihlali
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        addValidationError(cv.getRootBeanClass().getSimpleName(), ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
    }

    /**
     * Hata bilgilerini ayarlar ve childErrors listesine ekler.
     *
     * @param cause Oluşan hata
     */
    private void setDebugInfo(Throwable cause) {
        this.debugMessage = cause.getLocalizedMessage();
        StackTraceElement element = cause.getStackTrace()[0];
        this.clazz = element.getClassName();
        this.method = element.getMethodName();
        this.line = element.getLineNumber();
        addStackTraceToChildErrors(cause);
    }

    /**
     * Hatanın yığın izini childErrors listesine ekler.
     *
     * @param throwable Yazılacak hata
     */
    private void addStackTraceToChildErrors(Throwable throwable) {
        for (StackTraceElement element : throwable.getStackTrace()) {
            if (element.getClassName().startsWith(Paths.ConfigurationBasePackage)) {
                addChildError(new StackTraceError(element));
            }
        }
    }
}
