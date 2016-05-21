// CHECKSTYLE:OFF
package com.buba.thecarsalonmaven.controllers;

import javafx.fxml.FXML;
import org.slf4j.LoggerFactory;

public class RootFXMLController{

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(RootFXMLController.class);
    
    @FXML
    public void initialize() {
        logger.debug("RootFXMLController inicializálva.");
    }    
    
}
