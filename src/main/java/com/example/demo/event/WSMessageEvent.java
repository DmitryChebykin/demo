package com.example.demo.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.socket.WebSocketMessage;

public class WSMessageEvent extends ApplicationEvent {

    @Getter
    private  WebSocketMessage<?> message;
    @Getter
    private Object source;

    public WSMessageEvent(Object source, WebSocketMessage<?> message) {
        super(source);
        this.message = message;

    }
}
