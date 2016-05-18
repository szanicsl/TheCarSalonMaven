package com.buba.thecarsalonmaven.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
 
/**
 * Az autó színét modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "color")
@XmlAccessorType (XmlAccessType.FIELD)
public class Color {
    @XmlValue
    private String color;
    @XmlAttribute
    private int cost;

    /**
     * Üres konstruktora a {@link Color} osztálynak.
     */
    public Color() {
        this.color = "";
        this.cost = 0;
    }

    /**
     * A {@link Color} osztály összes tagját tartalmazó konstruktor.
     * @param color Az autó színe.
     * @param cost A szín ára.
     */
    public Color(String color, int cost) {
        this.color = color;
        this.cost = cost;
    }

    /**
     * 
     * @return Visszaadja a {@link Color} objektumunk által tárolt szín nevét.
     */
    public String getColor() {
        return color;
    }

    /**
     * Beállítja a {@link Color} objektumunk által tárolt szín nevét.
     * @param color A beállítandó szín.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 
     * @return Visszaadja a {@link Color} objektumunk által tárolt szín árát.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Beállítja a {@link Color} objektumunk által tárolt szín árát.
     * @param cost A szín ára.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return color;
    }
    
}
