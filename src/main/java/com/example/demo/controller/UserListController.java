package com.example.demo.controller;

import com.example.demo.event.PasswordInputEvent;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController extends AbstractFxController {

    private final ObservableList<User> userList = FXCollections.observableArrayList();

    private final FilteredList<User> filteredUserList = new FilteredList<>(userList);

    @FXML
    public VBox userContainer;

    @FXML
    public Label labHeader;

    @FXML
    public Label searchLabel;

    @FXML
    public ListView<User> userListView;

    @FXML
    public VBox vboxAttention;

    @FXML
    public Label labMessage;

    @FXML
    public Label labMessage1;

    @FXML
    public Button btnChange;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private ListCell<User> getListCell(ListView<User> listView) {
        return new ListCell<User>() {
            @Override
            public void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                setText(empty ? null : user.getFullName());
            }
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchLabel.setText("");
    }

    @PostConstruct
    public void init() {
        Stage stage = this.getStage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        vboxAttention.setVisible(true);
        userListView.setCellFactory(this::getListCell);

        List<User> users = userService.getUserList();
        userList.setAll(users);
        userListView.setItems(filteredUserList);
        User lastAuthoredUser = userService.getLastAuthoredUser();
        userListView.getSelectionModel().select(lastAuthoredUser);
        userListView.scrollTo(lastAuthoredUser);
    }

    public void onKeyPressed(KeyEvent keyEvent) {

    }

    public void onMouseClick(MouseEvent mouseEvent) {
        User selectedUser = userListView.getSelectionModel().getSelectedItem();
        applicationEventPublisher.publishEvent(new PasswordInputEvent(this, selectedUser.getId()));
    }

    public void letterButtonOnAction(ActionEvent actionEvent) {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(searchLabel.getText());

        searchLabel.setText(stringBuilder.append(buttonText).toString());

        filteredUserList.setPredicate(e -> e.getFullName().toUpperCase().contains(searchLabel.getText()));
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        searchLabel.setText("");
        filteredUserList.setPredicate(null);
    }

    public void btnChangeOnAction(ActionEvent actionEvent) {
        vboxAttention.setVisible(!vboxAttention.isVisible());
    }

    public void backspaceButtonOnAction(ActionEvent actionEvent) {
        StringBuilder stringBuilder = new StringBuilder(100);
        stringBuilder.append(searchLabel.getText());
        int newLength = Math.max(0, stringBuilder.length() - 1);
        stringBuilder.setLength(newLength);
        searchLabel.setText(stringBuilder.toString());
        filteredUserList.setPredicate(e -> e.getFullName().toUpperCase().contains(searchLabel.getText()));
    }
}
