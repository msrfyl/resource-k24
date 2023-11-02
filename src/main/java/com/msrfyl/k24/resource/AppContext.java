package com.msrfyl.k24.resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContext implements ApplicationContextAware {
    private static ApplicationContext CONTEXT;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        CONTEXT = context;
    }

    public static <T> T getBean(Class<T> clazz) {
        return CONTEXT.getBean(clazz);
    }
}