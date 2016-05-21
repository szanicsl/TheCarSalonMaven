package com.buba.thecarsalonmaven.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * A motor méretet modellező osztály, mely tartalmazza a motor méretét és annak a motornak az árát.
 * @author Szanics Levente
 */
@XmlRootElement(name = "motorsize")
@XmlAccessorType (XmlAccessType.FIELD)
public class MotorSize {
    @XmlValue
    private String size;
    @XmlAttribute
    private int cost;

    /**
     * Üres konstruktora a {@link MotorSize} osztálynak.
     */
    public MotorSize() {
        super();
        this.size = "";
        this.cost = 0;
    }

    /**
     * Konstruktor a {@link MotorSize} osztálynak az összes tagjával.
     * @param size A motor mérete.
     * @param cost A motor ára.
     */
    public MotorSize(String size, int cost) {
        this.size = size;
        this.cost = cost;
    }

    /**
     *
     * @return A motor mérete
     */
    public String getSize() {
        return size;
    }

    /**
     * Beállítja a motor méretét.
     * @param size A motor mérete.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     *
     * @return A motor ára.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Beállítja a motor árát.
     * @param cost A motor ára.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return size;
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
        final MotorSize other = (MotorSize) obj;
        if (this.cost != other.cost) {
            return false;
        }
        if (!Objects.equals(this.size, other.size)) {
            return false;
        }
        return true;
    }
    
    
    
}
