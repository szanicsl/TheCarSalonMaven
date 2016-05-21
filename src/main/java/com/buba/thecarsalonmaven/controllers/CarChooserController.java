// CHECKSTYLE:OFF
package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.MainApp;
import com.buba.thecarsalonmaven.handlers.ConfCarHandler;
import com.buba.thecarsalonmaven.models.ConfCar;
import com.buba.thecarsalonmaven.models.ConfCars;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class CarChooserController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CarChooserController.class);
    private MainApp main;
    private SAXParserFactory sPFactoryConfCar = SAXParserFactory.newInstance();
    private SAXParser parserConfCar;
    private ConfCarHandler handlerConfCar = new ConfCarHandler();
    private ConfCars confCars = new ConfCars();

    public void setMain(MainApp main) {
        this.main = main;
    }

    @FXML
    private Button configuratorButton;
    @FXML
    private Button ordersButton;
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
    private ListView<ConfCar> carListView;
    @FXML
    private Label carLabel;
    @FXML
    private Label motorLabel;
    @FXML
    private Label colorLabel;

    public Label getUserLabel() {
        return userLabel;
    }

    public void setUserLabel(Label userLabel) {
        this.userLabel = userLabel;
    }

    @FXML
    void handleSceneChooserButton(ActionEvent event) {
        
        logger.info("Kijelentkezés.");
        MainApp.rootPane.setCenter(MainApp.loginView);
        
    }

    @FXML
    void handleConfiguratorButton(ActionEvent event) {
        
        logger.info("Autókonfigurátor megnyitása.");
        main.createCarConfigurator();
        
    }

    @FXML
    void handleOrdersButton(ActionEvent event) {
        
        logger.info("Rendelések megnyitása.");
        main.createOrdersScene();
        
    }

    @FXML
    public void initialize() throws SAXException, IOException, ParserConfigurationException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream isConfCar = classloader.getResourceAsStream("xml/confcars.xml");
        parserConfCar = sPFactoryConfCar.newSAXParser();
        parserConfCar.parse(isConfCar, handlerConfCar);
        if (confCars.getConfCars().isEmpty()) {
            confCars.setConfCars(handlerConfCar.getConfCarList());
        }

        carimage.setImage(new Image("img/no.png"));
        userLabel.textProperty().bind(MainApp.onlineUser);
        showCar(null);
        carListView.setItems(confCars.getOConfCars());

        carListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ConfCar>() {

            @Override
            public void changed(ObservableValue<? extends ConfCar> observable, ConfCar oldValue, ConfCar newValue) {
                if (!carListView.getSelectionModel().isEmpty()) {
                    carLabel.setText(newValue.getName());
                    motorLabel.setText(newValue.getMotorTypes().toString());
                    colorLabel.setText(newValue.getColors().toString());
                    showCar(newValue);
                    logger.info(newValue.getName()+" kiválasztva.");
                }
            }
        });
        logger.debug("CarChooserController inicializálva.");
    }

    private void showCar(ConfCar car) {
        if (car != null) {
            try {
                carimage.setImage(new Image("img/" + Integer.toString(car.getId()) + ".png"));
                logger.info("Kép megváltoztatva.");
            } catch (Exception e) {
                carimage.setImage(new Image("img/no.png"));
                logger.error("Nincs elérhető kép az autóhoz.");
            }
        } else {
            carLabel.setText("");
            motorLabel.setText("");
            colorLabel.setText("");
        }
    }
}
