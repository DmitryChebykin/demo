package com.example.demo.config;

import com.example.demo.controller.*;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxControllerConfiguration {

    @SneakyThrows
    @Bean
    public WelcomeController welcomeController() {
        return AbstractFxController.init(new Stage(), "welcome.fxml");
    }

    @SneakyThrows
    @Bean
    public TestController testController() {
        return AbstractFxController.init(new Stage(), "B.fxml");
    }

    @SneakyThrows
    @Bean
    public UserListController userListController() {
        return AbstractFxController.init(new Stage(), "user_selection_view.fxml");
    }

    @SneakyThrows
    @Bean
    public PasswordInputController passwordInputController() {
        return AbstractFxController.init(new Stage(), "password_input_view.fxml");
    }

    @SneakyThrows
    @Bean
    public ErrorController errorController() {
        return AbstractFxController.init(new Stage(), "error_message_view.fxml");
    }

    @SneakyThrows
    @Bean
    public CustomerViewController customerViewController() {
        return AbstractFxController.init(new Stage(), "customer_screen_view.fxml");
    }

    @SneakyThrows
    @Bean
    public IdleController idleController() {
        return AbstractFxController.init(new Stage(), "idle_screnn_view.fxml");
    }
}
