package com.msrfyl.k24.resource.general;

import com.msrfyl.k24.resource.entity.member.MemberValidator;
import com.msrfyl.k24.resource.entity.user.WebUserValidator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RestValidator implements RepositoryRestConfigurer {

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener v) {
        v.addValidator("beforeCreate", new WebUserValidator());
        v.addValidator("beforeSave", new WebUserValidator());

        v.addValidator("beforeCreate", new MemberValidator());
        v.addValidator("beforeSave", new MemberValidator());
    }
}
