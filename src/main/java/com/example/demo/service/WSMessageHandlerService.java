package com.example.demo.service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public interface WSMessageHandlerService {
    void afterConnectionEstablished(WebSocketSession session);

    void handleMessage(WebSocketSession session, WebSocketMessage<?> message);

    void handleTransportError(WebSocketSession session, Throwable exception);

    void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus);
}
