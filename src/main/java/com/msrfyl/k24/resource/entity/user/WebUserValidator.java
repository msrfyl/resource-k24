package com.msrfyl.k24.resource.entity.user;

import com.msrfyl.k24.resource.general.MaxLengthValidation;
import com.msrfyl.k24.resource.general.RequiredValidation;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class WebUserValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return WebUser.class == clazz;
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {
        WebUser user = (WebUser) target;
        new RequiredValidation("username", user.getUsername());
        new RequiredValidation("password", user.getPassword());

        new MaxLengthValidation("username", user.getUsername(), 12);

        if (user.getName() != null) {
            new MaxLengthValidation("name", user.getName(), 100);
        }

    }

}
