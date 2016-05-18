package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * A motor típusát, vagy lehetséges típusait modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "motortypes")
@XmlAccessorType (XmlAccessType.FIELD)
public class MotorTypes {
    @XmlElement(name = "motortype")
    private List<MotorType> motorTypes = new ArrayList<MotorType>();

    /**
     *
     * @return A motor típusát, vagy lehetséges típusait tartalmazó lista.
     */
    public List<MotorType> getMotorTypes() {
        return motorTypes;
    }

    /**
     * Beállítja a motor típusát, vagy lehetséges típusait tartalmazó listát.
     * @param motorTypes A motor típusát, vagy lehetséges típusait tartalmazó lista.
     */
    public void setMotorTypes(List<MotorType> motorTypes) {
        this.motorTypes = motorTypes;
    }
    
    @Override
    public String toString() {
        String string = "";
        for(MotorType m : motorTypes){
            string = string +", "+ m.toString();
        }
        return string;
    }
}
