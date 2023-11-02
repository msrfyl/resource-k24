package com.msrfyl.k24.resource.general;

public class MaxLengthValidation extends HandledExceptionValidation {

    public MaxLengthValidation(String subject, String value, Integer length) {
        super(subject, value, subject + " is too length.", value.trim().length() <= length);
    }

}