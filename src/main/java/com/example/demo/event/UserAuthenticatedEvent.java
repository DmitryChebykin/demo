package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class UserAuthenticatedEvent extends ApplicationEvent {

    public UserAuthenticatedEvent(Object source) {
        super(source);
    }
}
