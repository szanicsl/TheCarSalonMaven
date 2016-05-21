// CHECKSTYLE:OFF
package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.logic.Logic;
import com.buba.thecarsalonmaven.MainApp;
import com.buba.thecarsalonmaven.models.Order;
import com.buba.thecarsalonmaven.models.Part;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;

public class OrdersSceneController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrdersSceneController.class);
    private Stage stage;
    private MainApp main;
    private Logic logic = new Logic();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Part> partList = FXCollections.observableArrayList();
    

    public void setMain(MainApp main) {
        this.main = main;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button removeButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<Order> orderListView;

    @FXML
    private ListView<Part> extrasList;

    @FXML
    private Label typeLabel;

    @FXML
    private Label motorTypeLabel;

    @FXML
    private Label motorSizeLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Label messageLabel;

    @FXML
    void handleCancelButton(ActionEvent event) {
        logger.info("Rendelések bezárása.");
        stage.close();
    }

    @FXML
    void handleRemoveButton(ActionEvent event) {
        Order order = orderListView.getSelectionModel().getSelectedItem();
        if(logic.removable(order)){
            orderList.clear();
            orderList.setAll(logic.getOrdersList());
            logic.removeOrder(orderList,order);
            orderList.clear();
            orderList.setAll(logic.getOrdersList());
            orderListView.setItems(orderList);
            messageLabel.setText("A rendelés törölve.");
            typeLabel.setText("");
            motorSizeLabel.setText("");
            motorTypeLabel.setText("");
            colorLabel.setText("");
            extrasList.getItems().clear();
            logger.info("Rendelés törölve.");
        }else{
            messageLabel.setText("Lejárt a 3 nap. A rendelés nem törölhető.");
            logger.info("Rendelés sikertelen törlése. Lejárt a 3 nap.");
        }
    }

    @FXML
    public void initialize() {
        messageLabel.setText("");
        logic.getOrdersList();
        if(!orderList.isEmpty()){
            orderList.clear();
        }

        logic.getOrders().getOrderList().stream().filter((o) -> (o.getUser().equals(MainApp.onlineUser.get()))).forEach((o) -> {
            orderList.add(o);
        });
        
        orderListView.setItems(orderList);

        orderListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {

            @Override
            public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                if(!orderListView.getSelectionModel().isEmpty()){
                    typeLabel.setText(newValue.getCar().getName());
                    motorTypeLabel.setText(newValue.getCar().getMotorTypes().getMotorTypes().get(0).getMname());
                    motorSizeLabel.setText(newValue.getCar().getMotorTypes().getMotorTypes().get(0).getMotorSizes().getMotorSizes().get(0).getSize());
                    colorLabel.setText(newValue.getCar().getColors().getColors().get(0).getColor());
                    partList.setAll(newValue.getParts().getParts());
                    extrasList.setItems(partList);
                    logger.info(newValue.getCar().getName()+", rendelt kocsi kiválasztva.");
                }
            }
        });
        logger.debug("OrdersSceneController inicializálva.");
    }

}
