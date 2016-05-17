package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author szani
 */
@XmlRootElement(name = "confcar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfCar{
    String name;
    @XmlAttribute
    int cost;
    Colors colors;
    MotorTypes motorTypes;
    int level;

    /**
     * Üres konstruktora a {@link ConfCar} osztálynak.
     */
    public ConfCar() {
        super();
        this.cost = 0;
    }

    /**
     * A {@link ConfCar} osztály konstruktora az összes taggal.
     * @param name A kocsi neve és/vagy típusa.
     * @param cost A kocsi alap ára.
     * @param colors A kocsi lehetséges színei.
     * @param motorTypes A kocsi motorjának lehetséges típusai.
     * @param level
     */
    public ConfCar(String name, int cost, Colors colors, MotorTypes motorTypes, int level) {
        this.name = name;
        this.cost = cost;
        this.colors = colors;
        this.motorTypes = motorTypes;
        this.level = level;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     *
     * @return
     */
    public Colors getColors() {
        return colors;
    }

    /**
     *
     * @param colors
     */
    public void setColors(Colors colors) {
        this.colors = colors;
    }

    /**
     *
     * @return
     */
    public MotorTypes getMotorTypes() {
        return motorTypes;
    }

    /**
     *
     * @param motorTypes
     */
    public void setMotorTypes(MotorTypes motorTypes) {
        this.motorTypes = motorTypes;
    }

    /**
     *
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return name;
    }
    
}