package com.example.demo.service;

import com.example.demo.event.WSMessageEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@RequiredArgsConstructor
public class WSMessageHandlerServiceImpl implements WSMessageHandlerService {
    private final ApplicationEventPublisher applicationEventPublisher;

    private final Set<WebSocketSession> webSocketSessions = new CopyOnWriteArraySet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        webSocketSessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        WSMessageEvent wsMessageEvent = new WSMessageEvent(this, message);
        applicationEventPublisher.publishEvent(wsMessageEvent);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        webSocketSessions.remove(session);
    }
}
