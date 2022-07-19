package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@Component
@RequiredArgsConstructor
public class StompSessionHandlerService implements StompSessionHandler {
    private final Set<StompSession> stompSessions = new CopyOnWriteArraySet<>();

    private final MessageBasketService messageBasketService;

    Map<String, StompSession.Subscription> subscriptions = new HashMap<>();

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Connection to STOMP server established.\n" +
                "Session: {}\n" +
                "Headers: {}", session, connectedHeaders);


        stompSessions.add(session);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {

    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Retrieved a transport error: {}", session);

        if (!session.isConnected()) {
            session.disconnect();
        }
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return  List.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Got a new message {}", payload);
        List<String> stompMessage = (List<String>) payload;
        messageBasketService.setStringList(stompMessage);
    }
}
