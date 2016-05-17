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
@XmlRootElement(name = "confcars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfCars {
    @XmlElement(name = "confcar")
    private List<ConfCar> confCars = new ArrayList<ConfCar>();
    
    /**
     *
     * @return
     */
    public List<ConfCar> getConfCars(){
        return confCars;
    }
    
    /**
     *
     * @param confCars
     */
    public void setConfCars(List<ConfCar> confCars){
        this.confCars = confCars;
        confCars.stream().forEach((c) -> {
            oConfCars.add(c);
        });
    }
    
    /**
     *
     */
    public ObservableList<ConfCar> oConfCars = FXCollections.observableArrayList();

    /**
     *
     * @return
     */
    public ObservableList<ConfCar> getOConfCars() {
        return oConfCars;
    }
}
