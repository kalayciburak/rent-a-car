package com.kalayciburak.commonpackage.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureYearValidator.class)
public @interface NotFutureYear {
    String message() default "Yıl değeri gelecekte bir yıl olamaz.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}