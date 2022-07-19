package com.example.demo.controller;

import com.example.demo.service.GreetingService;
import com.example.demo.service.WSMessageEventListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

  public class WelcomeController extends AbstractFxController {
    @FXML
    public Label welcomeLabel;
    @Autowired
    private   GreetingService greetingService;

//    @PostConstruct
    public void init(){
        this.getStage().show();
        welcomeLabel.setText(greetingService.getWelcomeGreeting());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
