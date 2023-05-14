package com.datvutech.ittrainingcenter.validation.model;

public abstract class ConfirmPasswordModel implements Password {
    public abstract String getConfirmPassword();

    public boolean isValid() {
        return getPassword() == null ? false : getPassword().equals(getConfirmPassword());
    }
}
