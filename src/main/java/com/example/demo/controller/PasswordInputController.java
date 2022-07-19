package com.example.demo.controller;

import com.example.demo.event.CancelPasswordInputEvent;
import com.example.demo.event.UserAuthenticatedEvent;
import com.example.demo.service.ApplicationStorageService;
import com.example.demo.service.UserService;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
public class PasswordInputController extends AbstractFxController {

    public static final int MAX_PASSWORD_LENGTH = 13;



    @FXML
    private PasswordField passwordField;

    @Autowired
    private UserService userService;

    @Autowired
    private ErrorController errorController;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationStorageService applicationStorageService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void passwordFieldOnKeyPressed(Event event) {
    }

    @FXML
    public void btnAcceptOnAction(ActionEvent actionEvent) {
        Optional<String> optionalId = applicationStorageService.getValidatedUserId();
        String passwordFieldText = passwordField.getText();

        if (optionalId.isPresent() && userService.isUserIdAndPasswordValid(optionalId.get(), passwordFieldText)) {

            log.info("Пользователь авторизован {}", userService.getUserList().stream().filter(e -> e.getId().equals(optionalId.get())).findFirst().get());

            applicationStorageService.setAuthenticatedUserId(optionalId.get());
            applicationEventPublisher.publishEvent(new UserAuthenticatedEvent(this));
        } else {
            log.info("Пароль неверный {}", passwordFieldText);

            Stage errorControllerStage = errorController.getStage();

            if (errorControllerStage.getOwner() == null) {
                errorControllerStage.initOwner(this.getStage());
            }

            errorController.errLabel.textProperty().set("Введен неверный пароль пользователя");

            errorControllerStage.showAndWait();
        }
    }

    @FXML
    public void btnCancelOnAction(ActionEvent actionEvent) {
        passwordField.clear();
        applicationEventPublisher.publishEvent(new CancelPasswordInputEvent(this));
    }

    @FXML
    public void btnBackspaceOnAction(ActionEvent actionEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(passwordField.getText());
        int length = passwordField.getText().length();
        String substring = stringBuilder.substring(0, Math.max(0, length - 1));
        passwordField.setText(substring);
        log.info("passwordField after backspace {}", passwordField.getText());
    }

    @FXML
    public void btnNumbersOnAction(ActionEvent actionEvent) {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        StringBuilder stringBuilder = new StringBuilder(MAX_PASSWORD_LENGTH);

        stringBuilder.append(passwordField.getText()).append(buttonText);
        passwordField.setText(stringBuilder.substring(0, Math.min(stringBuilder.length(), MAX_PASSWORD_LENGTH)));

        log.info("passwordField = {}", passwordField.getText());
    }

    @PostConstruct
    public void init() {
        passwordField.setText("");
        passwordField.requestFocus();
        this.getStage().initStyle(StageStyle.UNDECORATED);
    }
}
