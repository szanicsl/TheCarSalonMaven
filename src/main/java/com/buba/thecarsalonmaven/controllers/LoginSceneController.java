package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.MainApp;
import com.buba.thecarsalonmaven.handlers.UserHandler;
import static com.buba.thecarsalonmaven.MainApp.carChooser;
import com.buba.thecarsalonmaven.logic.Logic;
import com.buba.thecarsalonmaven.models.User;
import com.buba.thecarsalonmaven.models.Users;
import com.buba.thecarsalonmaven.xml.WriteXMLFile;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.rmi.activation.Activatable;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class LoginSceneController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CarConfiguratorController.class);
    private String path = System.getProperty("user.home") + "/.projectdatabase/";
    SAXParserFactory sPFactory = SAXParserFactory.newInstance();
    SAXParser parser;

    UserHandler handler = new UserHandler();
    private boolean create = true;
    private boolean contains = false;
    static Users users = new Users();
    private Logic logic = new Logic();

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button buttonlogin;
    @FXML
    private Label labellogin;
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private TextField userreg;
    @FXML
    private PasswordField passwordreg;
    @FXML
    private Label labelreg;
    @FXML
    private Button buttonreg;

    @FXML
    void handleLoginButtonAction(ActionEvent event) {
        contains = logic.login(user.getText(), password.getText());
        if (contains) {
            MainApp.rootPane.setCenter(carChooser);
            MainApp.onlineUser.set(user.getText());

            labellogin.setText("");
            contains = false;
            logger.info("Belépés sikeres: " + user.getText());
        } else {
            labellogin.setText("Rossz felhasználónév vagy jelszó.");
            logger.info("Sikertelen belépés: rossz felhasználónév/jelszó");
        }
    }

    @FXML
    void handleRegButtonAction(ActionEvent event) {
        users.getUsers().stream().forEach((u) -> {
            if (u.getUserName().equals(userreg.getText())) {
                create = false;
            }
        });
        if (create) {
            if (logic.userNameValidate(userreg.getText()) && logic.passwordValidate(passwordreg.getText())) {
                logic.register(userreg.getText(), passwordreg.getText());
            } else {
                labelreg.setText("A felhasználónév vagy jelszó túl rövid.");
            }
        } else {
            labelreg.setText("A felhasználónév használt.");
            logger.info("Sikertelen regisztráció: használt felhasználónév");
        }
        create = true;
    }

    @FXML
    void initialize() throws ParserConfigurationException, SAXException, IOException {
        parser = sPFactory.newSAXParser();
        parser.parse(new File(path + "users.xml"), handler);

        users.setUsers(handler.getUserList());
        users.getUsers().stream().forEach(System.out::println);
        logger.info("LoginSceneController objektum sikeres inicializálása");
    }
}
