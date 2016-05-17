package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 *
 * @author szani
 */
@XmlRootElement(name = "motorsizes")
@XmlAccessorType (XmlAccessType.FIELD)
public class MotorSizes {
    @XmlElement(name = "motorsize")
    private List<MotorSize> motorSizes = new ArrayList<MotorSize>();

    /**
     *
     * @return
     */
    public List<MotorSize> getMotorSizes() {
        return motorSizes;
    }

    /**
     *
     * @param motorSizes
     */
    public void setMotorSizes(List<MotorSize> motorSizes) {
        this.motorSizes = motorSizes;
    }
    
    @Override
    public String toString() {
        String string = "";
        for(MotorSize m : motorSizes){
            string = string +" "+ m.toString();
        }
        return "MotorSizes{" + "motorsizes=" + string + '}';
    }
}
