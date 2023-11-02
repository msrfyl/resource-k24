package com.msrfyl.k24.resource.general;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Map;

public class HandleException extends RuntimeException {

    private Map<String, Object> map;

    public HandleException(String message) {
        super(message);
    }

    public void setMap(Map<String, Object> map) {
        map.put("time", LocalDateTime.now());
        map.put("path", pathUrl());
        this.map = map;
    }

    private String pathUrl() {
        try {
            ServletRequestAttributes serReq = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            return serReq.getRequest().getRequestURL().toString();
        } catch (Exception e) {
            return "";
        }
    }

    public Map<String, Object> getMap() {
        return map;
    }

}