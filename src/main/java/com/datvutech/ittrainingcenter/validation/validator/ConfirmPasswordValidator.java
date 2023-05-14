package com.datvutech.ittrainingcenter.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.datvutech.ittrainingcenter.validation.annotation.ConfirmPassword;
import com.datvutech.ittrainingcenter.validation.model.ConfirmPasswordModel;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, ConfirmPasswordModel> {
    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        constraintAnnotation.message();
    }


    @Override
    public boolean isValid(ConfirmPasswordModel password, ConstraintValidatorContext context) {
        return password.isValid();
    }

}
