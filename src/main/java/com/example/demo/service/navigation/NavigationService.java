package com.example.demo.service.navigation;

import com.example.demo.api.rest.controller.ApplicationStateService;
import com.example.demo.config.GuiState;
import com.example.demo.controller.*;
import com.example.demo.event.*;
import com.example.demo.service.ApplicationStorageService;
import javafx.application.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NavigationService {

    private final WelcomeController welcomeController;

    private final TestController testController;

    private final PasswordInputController passwordInputController;

    private final UserListController userListController;

    private final CustomerViewController customerViewController;

    private final ApplicationStorageService applicationStorageService;

    private final IdleController idleController;

    private final ApplicationStateService applicationStateService;

    @EventListener
    public void changeUiMode(ChangeStateEvent changeStateEvent) {
        if (changeStateEvent.getState().equals(GuiState.CLOSED.name())) {
            Platform.runLater(() -> {
//                testController.getStage().show();
//                welcomeController.getStage().close();
            });

            return;
        }

        if (changeStateEvent.getState().equals((GuiState.IDLE.name()))) {
            Platform.runLater(() -> {
//                testController.getStage().close();
//                welcomeController.getStage().show();
            });
        }
    }

    @EventListener
    public void showPasswordInput(PasswordInputEvent passwordInputEvent) {
        applicationStorageService.setValidatedUserId(passwordInputEvent.getId());

        Platform.runLater(() -> {
            userListController.getStage().close();
            passwordInputController.getStage().show();
        });
    }

    @EventListener
    public void showUserListController(CancelPasswordInputEvent cancelPasswordInputEvent) {
        Platform.runLater(() -> {
            passwordInputController.getStage().close();
            userListController.getStage().show();
        });
    }

    @EventListener
    public void showIdleViewController(UserAuthenticatedEvent userAuthenticatedEvent) {
        Platform.runLater(() -> {
            passwordInputController.getStage().close();
            idleController.getStage().show();
            applicationStateService.setIsWebsocketListening(true);
        });
    }

    @EventListener
    public void handleShowStageEvent(ShowStageEvent showStageEvent) {
        Object source = showStageEvent.getSource();
        if (source instanceof IdleController) {
            applicationStateService.setIsWebsocketListening(true);
            log.info("Слушаем сокет");
            applicationStateService.setActiveController(source);
        }
    }

    public void showCustomerViewController() {
        Platform.runLater(() -> {

            idleController.getStage().close();
            customerViewController.getStage().show();
        });
    }
}
