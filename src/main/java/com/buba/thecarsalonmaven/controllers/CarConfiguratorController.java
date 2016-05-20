package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.handlers.ConfCarHandler;
import com.buba.thecarsalonmaven.logic.Logic;
import com.buba.thecarsalonmaven.handlers.PartHandler;
import com.buba.thecarsalonmaven.models.Color;
import com.buba.thecarsalonmaven.models.ConfCar;
import com.buba.thecarsalonmaven.models.ConfCars;
import com.buba.thecarsalonmaven.models.MotorSize;
import com.buba.thecarsalonmaven.models.MotorType;
import com.buba.thecarsalonmaven.models.Part;
import com.buba.thecarsalonmaven.models.Parts;
import java.io.IOException;
import java.io.InputStream;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarConfiguratorController {
    private static final Logger logger = LoggerFactory.getLogger(CarConfiguratorController.class);
    private Logic logic = new Logic();
    private Stage stage;

    private SAXParserFactory sPFactoryPart = SAXParserFactory.newInstance();
    private SAXParser parserPart;
    private SAXParserFactory sPFactoryConfCar = SAXParserFactory.newInstance();
    private SAXParser parserConfCar;
    
    private ObservableList<Part> addedExtraList = FXCollections.observableArrayList();
    private ObservableList<MotorType> motorTypesList = FXCollections.observableArrayList();  
    private ObservableList<Color> colorsList = FXCollections.observableArrayList();   
    private ObservableList<MotorSize> motorSizeList = FXCollections.observableArrayList();
    private ObservableList<Part> oParts = FXCollections.observableArrayList();
    
    private PartHandler handlerPart = new PartHandler();
    private ConfCarHandler handlerConfCar = new ConfCarHandler();
    
    private Parts parts = new Parts();
    private ConfCars confCars = new ConfCars();
    
    private ConfCar confCar = new ConfCar();
    private Color color = new Color();
    private MotorSize motorSize = new MotorSize();
    private MotorType motorType = new MotorType();
    
    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button orderButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<ConfCar> typeChooser;

    @FXML
    private ChoiceBox<MotorType> motorTypeChooser;

    @FXML
    private ChoiceBox<MotorSize> motorSizeChooser;

    @FXML
    private ChoiceBox<Color> colorChooser;

    @FXML
    private ChoiceBox<Part> extraChooser;

    @FXML
    private Label costLabel;

    @FXML
    private ListView<Part> extraListView;

    @FXML
    private Button addExtra;

    @FXML
    private Button deleteExtra;

    @FXML
    void handleAddButton(ActionEvent event) {
        if(extraListView.getItems().contains(extraChooser.getSelectionModel().getSelectedItem())){
            logger.info("Már tartalmazza a lista az extrát.");
        }else if(extraChooser.getSelectionModel().getSelectedItem() != null){
            addedExtraList.add(extraChooser.getSelectionModel().getSelectedItem());
            extraListView.setItems(addedExtraList);
            costLabel.setText(String.valueOf(logic.getCost(confCar, color, motorSize, addedExtraList.subList(0, addedExtraList.size()))));
        }
    }

    @FXML
    void handleCancelButton(ActionEvent event) {
        stage.close();
    }
    
    @FXML
    void handleOrderButton(ActionEvent event) {
        if(typeChooser.getSelectionModel().getSelectedItem()!=null
                && motorTypeChooser.getSelectionModel().getSelectedItem()!=null
                && motorSizeChooser.getSelectionModel().getSelectedItem()!=null
                && colorChooser.getSelectionModel().getSelectedItem()!=null){
            
            
                logic.makeOrder(typeChooser.getSelectionModel().getSelectedItem()
                        ,motorSizeChooser.getSelectionModel().getSelectedItem()
                        ,motorTypeChooser.getSelectionModel().getSelectedItem()
                        ,colorChooser.getSelectionModel().getSelectedItem()
                        ,Integer.parseInt(costLabel.getText()),addedExtraList);
                
                logger.info("Rendelés történt.");
            
            
        }else{
            logger.warn("Sikertelen rendelés. Nem lett választva minden listából elem.");
        }
    }

    @FXML
    void handleDeleteButton(ActionEvent event) {
        int selected = extraListView.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            extraListView.getItems().remove(selected);
        }
        costLabel.setText(String.valueOf(logic.getCost(confCar, color, motorSize, addedExtraList.subList(0, addedExtraList.size()))));
    }

    @FXML
    private void initialize() throws SAXException, IOException, ParserConfigurationException,NullPointerException {  
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream isConfCar = classloader.getResourceAsStream("xml/confcars.xml");
        InputStream isParts = classloader.getResourceAsStream("xml/parts.xml");
        
        parserPart = sPFactoryPart.newSAXParser();
        parserPart.parse(isParts, handlerPart);

        parserConfCar = sPFactoryConfCar.newSAXParser();
        parserConfCar.parse(isConfCar, handlerConfCar);
        
        if(confCars.getConfCars().isEmpty()){
            confCars.setConfCars(handlerConfCar.getConfCarList());
        }

        typeChooser.setItems(confCars.getOConfCars());
        
        typeChooser.valueProperty().addListener(new ChangeListener<ConfCar>(){
            @Override
            public void changed(ObservableValue<? extends ConfCar> observable, ConfCar oldValue, ConfCar newValue) {
                oParts.clear();
                parts.getParts().clear();
                addedExtraList.clear();
                color = new Color();
                motorSize = new MotorSize();
                
                motorSizeChooser.getSelectionModel().clearSelection();
                colorChooser.getSelectionModel().clearSelection();
                motorTypeChooser.getSelectionModel().clearSelection();

                confCar = typeChooser.getSelectionModel().getSelectedItem();
                 
                motorTypesList.setAll(confCar.getMotorTypes().getMotorTypes());
                motorTypeChooser.setItems(motorTypesList);
                
                colorsList.setAll(confCar.getColors().getColors());
                colorChooser.setItems(colorsList);
                costLabel.setText(String.valueOf(logic.getCost(confCar, color, motorSize, addedExtraList.subList(0, addedExtraList.size()))));
                handlerPart.getPartList().stream().filter((p) -> (logic.usable(confCar, p))).forEach((p) -> {
                    parts.getParts().add(p);
                });
                oParts.setAll(parts.getParts());
                extraChooser.setItems(oParts);
            }
        });
        
        motorTypeChooser.valueProperty().addListener(new ChangeListener<MotorType>(){
            @Override
            public void changed(ObservableValue<? extends MotorType> observable, MotorType oldValue, MotorType newValue) {
                motorType = motorTypeChooser.getSelectionModel().getSelectedItem();;
                if(motorType == null){
                    motorType = new MotorType();
                }
                motorSizeList.setAll(motorType.getMotorSizes().getMotorSizes());
                motorSizeChooser.setItems(motorSizeList);
                
                costLabel.setText(String.valueOf(logic.getCost(confCar, color, motorSize, addedExtraList.subList(0, addedExtraList.size()))));
                
            }
        });
        
        colorChooser.valueProperty().addListener(new ChangeListener<Color>(){
            @Override
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                color = colorChooser.getSelectionModel().getSelectedItem();
                if(color == null){
                    color = new Color();
                }
                costLabel.setText(String.valueOf(logic.getCost(confCar, color, motorSize, addedExtraList.subList(0, addedExtraList.size()))));
            }
        });
        
        motorSizeChooser.valueProperty().addListener(new ChangeListener<MotorSize>(){
            @Override
            public void changed(ObservableValue<? extends MotorSize> observable, MotorSize oldValue, MotorSize newValue) {
                motorSize = motorSizeChooser.getSelectionModel().getSelectedItem();
                if(motorSize == null){
                    motorSize = new MotorSize();
                }
                costLabel.setText(String.valueOf(logic.getCost(confCar, color, motorSize, addedExtraList.subList(0, addedExtraList.size()))));
            }
        });
        
    }
}
