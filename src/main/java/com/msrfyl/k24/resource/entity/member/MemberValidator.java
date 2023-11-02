package com.msrfyl.k24.resource.entity.member;

import com.msrfyl.k24.resource.general.MaxLengthValidation;
import com.msrfyl.k24.resource.general.RequiredValidation;
import org.jetbrains.annotations.NotNull;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberValidator implements Validator {

    @Override
    public boolean supports(@NotNull Class<?> clazz) {
        return Member.class == clazz;
    }

    @Override
    public void validate(@NotNull Object target, @NotNull Errors errors) {

        Member member = (Member) target;
        new RequiredValidation("name", member.getName());
        new RequiredValidation("password", member.getPassword());
        new RequiredValidation("numberKtp", member.getNumberKtp());
        new RequiredValidation("phoneNumber", member.getPhoneNumber());
        new RequiredValidation("email", member.getEmail());

        new MaxLengthValidation("name", member.getName(), 100);
        new MaxLengthValidation("numberKtp", member.getNumberKtp(), 50);
        new MaxLengthValidation("email", member.getEmail(), 50);
        new MaxLengthValidation("numberKtp", member.getPhoneNumber(), 15);

    }

}
