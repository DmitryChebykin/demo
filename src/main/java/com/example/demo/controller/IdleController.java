package com.example.demo.controller;

import com.example.demo.event.ShowStageEvent;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public class IdleController extends AbstractFxController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onMouseClicked(MouseEvent mouseEvent) {

    }

    public void onTouchPressed(TouchEvent touchEvent) {

    }

    public void btnSettingsOnAction(ActionEvent actionEvent) {

    }

    @PostConstruct
    public void init() {
        Stage stage = this.getStage();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setOnShowing(e -> applicationEventPublisher.publishEvent(new ShowStageEvent(this)));
    }
}
