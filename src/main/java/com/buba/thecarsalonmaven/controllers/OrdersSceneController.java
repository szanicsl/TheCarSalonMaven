package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.Logic;
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

public class OrdersSceneController {

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
        stage.close();
    }

    @FXML
    void handleRemoveButton(ActionEvent event) {
        Order order = orderListView.getSelectionModel().getSelectedItem();
        if(logic.removable(order)){
            orderList.setAll(logic.removeOrder(orderList,order));
            orderListView.setItems(orderList);
            messageLabel.setText("A rendelés törölve.");
            orderListView.getSelectionModel().clearSelection();
        }else{
            messageLabel.setText("Lejárt a 3 nap. A rendelés nem törölhető.");
        }
    }

    @FXML
    public void initialize() {
        messageLabel.setText("");
        logic.getOrdersList();
        if(!orderList.isEmpty()){
            orderList.clear();
        }

        Logic.orders.getOrderList().stream().filter((o) -> (o.getUser().equals(MainApp.onlineUser.get()))).forEach((o) -> {
            System.out.println("Online: "+MainApp.onlineUser.get()+", User: "+o.getUser());
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
                    System.out.println("Selected item: " + newValue);
                }
            }
        });
    }

}
