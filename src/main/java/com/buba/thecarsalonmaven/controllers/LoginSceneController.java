package com.buba.thecarsalonmaven.controllers;

import com.buba.thecarsalonmaven.MainApp;
import com.buba.thecarsalonmaven.handlers.UserHandler;
import static com.buba.thecarsalonmaven.MainApp.carChooser;
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
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class LoginSceneController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CarConfiguratorController.class);
    private String path = System.getProperty("user.home")+"/.projectdatabase/";
    SAXParserFactory sPFactory = SAXParserFactory.newInstance();
    SAXParser parser;

    UserHandler handler = new UserHandler();
    private boolean create = true;
    private boolean contains = false;
    static Users users = new Users();

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
        users.getUsers().stream().forEach((u) -> {
            if (u.getUserName().equals(user.getText()) && u.getPassword().equals(password.getText())) {
                contains = true;
            }
        });
        if (contains) {
            MainApp.rootPane.setCenter(carChooser);
            MainApp.onlineUser.set(user.getText());

            labellogin.setText("");
            contains = false;
            logger.info("Belépés sikeres: "+user.getText());
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
            try {

                WriteXMLFile w = new WriteXMLFile();
                w.init(0);
                users.getUsers().add(new User(userreg.getText(), passwordreg.getText()));
                w.getJaxbMarshaller().marshal(users, new File(path+"users.xml"));
                labelreg.setText("Sikeres regisztráció. Mostmár beléphetsz.");
                logger.info("Sikeres regisztráció"+ userreg.getText()+"néven.");
            } catch (JAXBException ex) {
                logger.error("Users.xml létrehozási/módosítási hiba.");
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
