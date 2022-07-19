package com.example.demo.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;

@Setter
@Getter
public abstract class AbstractFxController implements Initializable {
    private Scene scene;

    private Stage stage;

    public static <T extends AbstractFxController> T init(Stage stage, String fxmlSource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();

        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fxmlSource);

        Parent rootParent = fxmlLoader.load(resourceAsStream);

        Scene scene = new Scene(rootParent);
        scene.setFill(Color.TRANSPARENT);

        stage.setScene(scene);

        T controller = fxmlLoader.getController();

        controller.setScene(scene);
        controller.setStage(stage);

        return controller;
    }

    public void initialize() {
    }
}
