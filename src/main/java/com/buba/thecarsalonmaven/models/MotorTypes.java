package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
 
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
            if(string.equals("")){
                string = m.toString();
            }else{
            string = string +", "+ m.toString();
            }
        }
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MotorTypes other = (MotorTypes) obj;
        if (!Objects.equals(this.motorTypes, other.motorTypes)) {
            return false;
        }
        return true;
    }
    
    
}
