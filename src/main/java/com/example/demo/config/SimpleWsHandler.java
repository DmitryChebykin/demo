package com.example.demo.config;

import com.example.demo.service.WSMessageHandlerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
@Component
@RequiredArgsConstructor
@Slf4j
public class SimpleWsHandler implements WebSocketHandler {
    private final WSMessageHandlerService wsMessageHandlerService;

    /**
     * Called when WS connects to the server.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        wsMessageHandlerService.afterConnectionEstablished(session);
        log.info("Connection established");
    }

    /**
     * Main method to handle server messages.
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info("Message send to handleService");
        wsMessageHandlerService.handleMessage(session, message);
    }

    /**
     * Error handling.
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        wsMessageHandlerService.handleTransportError(session, exception);

    }

    /**
     * Called when WS is closed.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        wsMessageHandlerService.afterConnectionClosed(session, closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        // TODO Auto-generated method stub
        return false;
    }
}

