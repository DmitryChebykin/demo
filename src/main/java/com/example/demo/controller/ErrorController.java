package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public class ErrorController extends AbstractFxController {
    @FXML
    public Label errLabel;

    @FXML
    public Button btnConfirm;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void btnConfirmOnAction(ActionEvent actionEvent) {
        Stage stage = this.getStage();
        stage.close();
    }

    @PostConstruct
    public void init() {
        Stage stage = this.getStage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
    }
}
