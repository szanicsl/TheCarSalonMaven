package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 *
 * @author szani
 */
@XmlRootElement(name = "parts")
@XmlAccessorType (XmlAccessType.FIELD)
public class Parts {
    @XmlElement(name = "part")
    private List<Part> parts = new ArrayList<Part>();

    /**
     *
     * @return
     */
    public List<Part> getParts() {
        return parts;
    }

    /**
     *
     * @param parts
     */
    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
    
}
