package com.buba.thecarsalonmaven.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author szani
 */
@XmlRootElement(name = "motorsize")
@XmlAccessorType (XmlAccessType.FIELD)
public class MotorSize {
    @XmlValue
    String size;
    @XmlAttribute
    int cost;

    /**
     * Üres konstruktora a {@link MotorSize} osztálynak.
     */
    public MotorSize() {
        super();
        this.size = "";
        this.cost = 0;
    }

    /**
     *
     * @param size
     * @param cost
     */
    public MotorSize(String size, int cost) {
        this.size = size;
        this.cost = cost;
    }

    /**
     *
     * @return
     */
    public String getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     *
     * @return
     */
    public int getCost() {
        return cost;
    }

    /**
     *
     * @param cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return size;
    }
    
    
}
