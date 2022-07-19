package com.example.demo.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PasswordInputEvent extends ApplicationEvent {
    private String id;

    public PasswordInputEvent(Object source, String id) {
        super(source);
        this.id = id;
    }
}
