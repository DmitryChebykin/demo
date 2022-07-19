package com.example.demo.api.rest.controller;

import com.example.demo.event.ChangeStateEvent;
import com.example.demo.config.GuiState;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketConnectionManager;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
public class ApplicationStateService {
    @Getter
    private final AtomicReference<GuiState> guiStateAtomicReference = new AtomicReference<>();

    private final WebSocketConnectionManager wsConnectionManager;

    private final ApplicationEventPublisher applicationEventPublisher;

    @Getter
    private final AtomicBoolean isWebsocketListening = new AtomicBoolean(false);

    @Getter
    private final AtomicReference<Object> activeController = new AtomicReference<>();

    @PostConstruct
    public void setClosedState() {
        guiStateAtomicReference.set(GuiState.CLOSED);
        applicationEventPublisher.publishEvent(new ChangeStateEvent(GuiState.CLOSED.name()));
        wsConnectionManager.stop();
    }

    public void setGuiStateAtomicReference(GuiState guiState) {
        guiStateAtomicReference.set(guiState);

        applicationEventPublisher.publishEvent(new ChangeStateEvent(this, guiState.name()));

        if (!guiState.equals(GuiState.CLOSED)) {
            wsConnectionManager.start();
            return;
        }

        setClosedState();
    }

    public void setIsWebsocketListening(boolean b) {
        isWebsocketListening.set(b);
    }

    public void setActiveController(Object source) {
        activeController.set(source);
    }
}
