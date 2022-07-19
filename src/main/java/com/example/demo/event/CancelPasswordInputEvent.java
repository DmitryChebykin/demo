package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class CancelPasswordInputEvent extends ApplicationEvent {

    public CancelPasswordInputEvent(Object source) {
        super(source);
    }
}
