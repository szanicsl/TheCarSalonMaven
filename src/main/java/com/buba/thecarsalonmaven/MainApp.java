package com.buba.thecarsalonmaven;

import com.buba.thecarsalonmaven.controllers.CarChooserController;
import com.buba.thecarsalonmaven.controllers.CarConfiguratorController;
import com.buba.thecarsalonmaven.controllers.OrdersSceneController;
import com.buba.thecarsalonmaven.models.Order;
import com.buba.thecarsalonmaven.xml.FileCreator;
import com.sun.xml.internal.bind.v2.runtime.property.ValueProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainApp extends Application {
    private static final Logger logger = LoggerFactory.getLogger(CarConfiguratorController.class);
    private Stage primaryStage;
    public static BorderPane rootPane;
    public static AnchorPane carChooser;
    public static AnchorPane loginView;
    public static StringProperty onlineUser = new SimpleStringProperty("");
    public static CarChooserController ccc;
    private FileCreator fileCreator;

    public static List<Order> orderList = new ArrayList<Order>();

    @Override
    public void start(Stage primaryStage) {
        fileCreator = new FileCreator();

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The Car Salon");
        createRootPane();
        createCarView();
        createLoginView();
        this.primaryStage.setMinWidth(800);
        this.primaryStage.setMaxWidth(1000);
        this.primaryStage.setMinHeight(525);
        this.primaryStage.setMaxHeight(700);
        logger.info("A program betöltése megtörtént.");
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void createRootPane() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/RootFXML.fxml"));
            rootPane = (BorderPane) loader.load();

            Scene scene = new Scene(rootPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            logger.info("Root view létrejött");
        } catch (IOException e) {
            logger.error(e.getClass().getName()+": A RootFXML.fxml fájl beolvasása sikertelen");
        }
    }

    private void createLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/LoginScene.fxml"));
            loginView = (AnchorPane) loader.load();

            rootPane.setCenter(loginView);
            logger.info("Login view létrejött");
        } catch (IOException e) {
            logger.error(e.getClass().getName()+": A LoginScene.fxml fájl beolvasása sikertelen");
        }
    }

    public void createCarView() {
        try {
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/fxml/CarChooserScene.fxml"));

            carChooser = (AnchorPane) loader.load();

            rootPane.setCenter(carChooser);

            ccc = loader.getController();
            ccc.setMain(this);
            logger.info("CarChooser view létrejött");
        } catch (IOException e) {
            logger.error(e.getClass().getName()+": A CarChooserScene.fxml fájl beolvasása sikertelen");
        }
    }

    public void createCarConfigurator() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/CarConfiguratorScene.fxml"));

        try {
            AnchorPane edit = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Autó konfigurátor");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(edit);
            stage.setScene(scene);

            CarConfiguratorController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
            logger.info("Autó konfigurátor ablak létrejött");
        } catch (IOException e) {
            logger.error(e.getClass().getName()+": A CarConfiguratorScene.fxml fájl beolvasása sikertelen");
        }
    }
    
    public void createOrdersScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/fxml/OrdersScene.fxml"));

        try {
            AnchorPane orders = (AnchorPane) loader.load();

            Stage stage = new Stage();
            stage.setTitle("Rendelések");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(primaryStage);

            Scene scene = new Scene(orders);
            stage.setScene(scene);

            OrdersSceneController controller = loader.getController();
            controller.setStage(stage);

            stage.showAndWait();
            logger.info("Rendelések ablak létrejött");
        } catch (IOException e) {
            logger.error(e.getClass().getName()+": Az OrderScene.fxml fájl beolvasása sikertelen");
        }
    }
}
