package com.example.demo;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class FxBootApplication extends Application {
    private static String[] args;
    private ConfigurableApplicationContext applicationContext;


    public static void main(String[] args) {
        FxBootApplication.args = args;
        Application.launch();

    }

    @Override
    public void stop() throws Exception {
        super.stop();
        applicationContext.stop();
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext = SpringApplication.run(FxBootApplication.class, args);
    }


}
