package com.datvutech.ittrainingcenter.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.datvutech.ittrainingcenter.validation.validator.ConfirmPasswordValidator;

@Documented
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfirmPassword {
    String message() default "{com.datvutech.ittrainingcenter.validation.annotation.ConfirmPassword.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
