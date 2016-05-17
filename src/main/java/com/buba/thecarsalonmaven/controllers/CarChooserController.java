package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.MainApp;
import com.buba.thecarsalonmaven.models.Car;
import com.buba.thecarsalonmaven.xml.ReadXMLFile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class CarChooserController {

    private MainApp main;

    public void setMain(MainApp main) {
        this.main = main;
    }

    public ReadXMLFile xmlCars = new ReadXMLFile();
    
    @FXML
    private Button configuratorButton;
    @FXML
    private Button ordersButton;
    @FXML
    private Label label;
    @FXML
    private Label userLabel = new Label("");
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button sceneChooser;
    @FXML
    private ImageView carimage;
    @FXML
    private TableView<Car> table;
    @FXML
    private TableColumn<Car, String> name;
    @FXML
    private TableColumn<Car, String> color;
    @FXML
    private TableColumn<Car, String> motorType;
    @FXML
    private TableColumn<Car, String> motorSize;
    @FXML
    private TableColumn<Car, String> doorsNum;

    public Label getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(Label userLabel) {
        this.userLabel = userLabel;
    }

    @FXML
    void handleSceneChooserButton(ActionEvent event) {
        MainApp.rootPane.setCenter(MainApp.loginView);
    }

    @FXML
    void handleConfiguratorButton(ActionEvent event) {
        main.createCarConfigurator();
    }
    
    @FXML
    void handleOrdersButton(ActionEvent event) {
        main.createOrdersScene();
    }

    @FXML
    public void initialize() throws SAXException, IOException, ParserConfigurationException {
        xmlCars.readCars();
        carimage.setImage(new Image("img/no.png"));
        table.setItems(xmlCars.readyForSaleCarsList);
        name.setCellValueFactory(cd -> cd.getValue().getNameProperty());
        color.setCellValueFactory(cd -> cd.getValue().getColorProperty());
        motorType.setCellValueFactory(cd -> cd.getValue().getMotorTypeProperty());
        motorSize.setCellValueFactory(cd -> cd.getValue().getMotorSizeProperty());
        doorsNum.setCellValueFactory(cd -> cd.getValue().getDoorsNumberProperty());
        userLabel.textProperty().bind(MainApp.onlineUser);
        showCar(null);
        table.getSelectionModel().selectedItemProperty().addListener(
                (o, oldValue, newValue) -> showCar(newValue)
        );
    }

    private void showCar(Car car) {
        if (car != null) {
            label.setText(car.toLabelString());
            try {
                carimage.setImage(new Image("img/" + car.getId() + ".png"));
            } catch (Exception e) {
                carimage.setImage(new Image("img/no.png"));
            }
        } else {
            label.setText("");
        }
    }
}
