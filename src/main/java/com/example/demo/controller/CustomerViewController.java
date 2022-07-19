package com.example.demo.controller;

import com.example.demo.model.GoodRowModel;
import com.example.demo.service.MessageBasketService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerViewController extends AbstractFxController {
    private final ObservableList<GoodRowModel> stringObservableList = FXCollections.observableArrayList();

    private final Property<ObservableList<GoodRowModel>> goodListProperty = new SimpleObjectProperty<>(stringObservableList);


    @FXML
    public Label sumText;

    @FXML
    public VBox checkContainer;

    @FXML
    public HBox cardBox;

    @FXML
    public Text labKopilkaNumber;

    @FXML
    public Text labKopilkaBalance;

    @FXML
    public Text labKopilkaChips;

    @FXML
    public Button btnWorkflow;

    @FXML
    public Button btnQuantity;

    @FXML
    public Button btnBarcode;

    @FXML
    public VBox prohibitedContainer;

    @FXML
    public Button btnExciseScanInfo;

    @FXML
    public VBox vBoxProhibited;

    @FXML
    public Label labNeedConfirm;

    @FXML
    public Button btnPay;

    @FXML
    public TableColumn<String, GoodRowModel> goodListTableColumn;

    @Autowired
    private MessageBasketService messageBasketService;

    @FXML
    private TableView<GoodRowModel> goodListTableView;

    public void btnSettingsOnAction(ActionEvent actionEvent) {
    }

    public void btnBarcodeOnAction(ActionEvent actionEvent) {
    }

    public void btnQuantityOnAction(ActionEvent actionEvent) {
    }

    public void btnExciseScanInfoOnAction(ActionEvent actionEvent) {
    }

    public void btnPayOnAction(ActionEvent actionEvent) {
    }

    public void btnWorkflowOnAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @PostConstruct
    public void init() {
        this.getStage().initStyle(StageStyle.UNDECORATED);
        goodListTableView.setItems(stringObservableList);

        goodListTableView.itemsProperty().bind(goodListProperty);
//        goodListTableView.setItems(stringObservableList);
        Bindings.bindContent(messageBasketService.getGoodRowModelList(), stringObservableList);
    }
}
