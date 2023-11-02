package com.msrfyl.k24.resource;

import com.msrfyl.k24.resource.entity.user.WebUser;
import com.msrfyl.k24.resource.entity.user.WebUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventListeners {

    @Autowired
    private WebUserRepository webUserRepository;

    @EventListener(ApplicationReadyEvent.class)
    private void appOnReady() {
        Logger logger = LoggerFactory.getLogger(EventListener.class);
        logger.info("application is ready");

        if (webUserRepository.findById(1).isEmpty()) {
            logger.info("save default web user");
            webUserRepository.save(
                    new WebUser("admin", "admin", "admin")
            );
        }

    }

}
