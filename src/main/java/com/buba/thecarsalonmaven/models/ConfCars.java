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
 * {@link ConfCar} objektumok listáját tartalmazó osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "confcars")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfCars {
    @XmlElement(name = "confcar")
    private List<ConfCar> confCars = new ArrayList<ConfCar>();
    
    /**
     *
     * @return Visszaadja az autók listáját.
     */
    public List<ConfCar> getConfCars(){
        return confCars;
    }
    
    /**
     * Beállítja az autók {@link ObservableList} típusú listáját.
     * @param confCars A lista, melyre beállítjuk a confCars és az oConfCars listákat.
     */
    public void setConfCars(List<ConfCar> confCars){
        this.confCars = confCars;
        confCars.stream().forEach((c) -> {
            oConfCars.add(c);
        });
    }
    
    /**
     * Autók listája, mely a JavaFX néhány osztályához szükséges.
     */
    public ObservableList<ConfCar> oConfCars = FXCollections.observableArrayList();

    /**
     *
     * @return Visszaadja az autók {@link ObservableList} típusú listáját.
     */
    public ObservableList<ConfCar> getOConfCars() {
        return oConfCars;
    }
}
