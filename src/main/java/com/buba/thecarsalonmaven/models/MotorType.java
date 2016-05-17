package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 *
 * @author szani
 */
@XmlRootElement(name = "motortype")
@XmlAccessorType (XmlAccessType.FIELD)
public class MotorType {
    String mName;
    MotorSizes motorSizes;

    /**
     * Üres konstruktora a {@link MotorType} osztálynak.
     */
    public MotorType() {
        super();
        List<MotorSize> list = new ArrayList<MotorSize>();
        list.add(new MotorSize());
        this.mName = "";
        this.motorSizes = new MotorSizes();
        this.motorSizes.setMotorSizes(list);
    }

    /**
     *
     * @param mName
     * @param motorSizes
     */
    public MotorType(String mName, MotorSizes motorSizes) {
        this.mName = mName;
        this.motorSizes = motorSizes;
    }

    /**
     *
     * @return
     */
    public String getMname() {
        return mName;
    }

    /**
     *
     * @param mName
     */
    public void setMname(String mName) {
        this.mName = mName;
    }

    /**
     *
     * @return
     */
    public MotorSizes getMotorSizes() {
        return motorSizes;
    }

    /**
     *
     * @param motorSizes
     */
    public void setMotorSizes(MotorSizes motorSizes) {
        this.motorSizes = motorSizes;
    }

    @Override
    public String toString() {
        return mName;
    }
    
}
