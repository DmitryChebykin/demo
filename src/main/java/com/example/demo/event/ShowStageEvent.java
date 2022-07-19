package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

public class ShowStageEvent extends ApplicationEvent {

    public ShowStageEvent(Object source) {
        super(source);
     }
}
