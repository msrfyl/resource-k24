package com.msrfyl.k24.resource.general;

public class RequiredValidation extends HandledExceptionValidation {
    public RequiredValidation(String subject, String value) {
        super(subject, value, subject + " is required.", !value.isBlank());
    }

}