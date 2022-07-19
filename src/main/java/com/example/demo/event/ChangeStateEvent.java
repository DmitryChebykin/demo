package com.example.demo.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class ChangeStateEvent extends ApplicationEvent {
    private String state;

    public ChangeStateEvent(Object source) {
        super(source);
    }

    public ChangeStateEvent(Object source, String state) {
        super(source);
        this.state = state;
    }
}
