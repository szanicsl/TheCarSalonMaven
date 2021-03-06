package com.buba.thecarsalonmaven.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Az autókat modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "confcar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfCar{

    private String name;
    @XmlAttribute
    private int id;
    @XmlAttribute
    private int cost;
    private Colors colors;
    private MotorTypes motorTypes;
    private int level;

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
     * @param level A kocsi szintje.
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
     * @return Visszatér a kocsi nevével.
     */
    public String getName() {
        return name;
    }

    /**
     * Beállítja a kocsi nevét.
     * @param name A beállítandó név.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Visszaadja a kocsi id-ját.
     */
    public int getId() {
        return id;
    }

    /**
     * Beállítja a kocsi id-ját.
     * @param id A beállítandó id.
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     *
     * @return Visszaadja a kocsi árát.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Beállítja a kocsi árát.
     * @param cost A beállítandó ár.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * 
     * @return Visszaadja a kocsi színeinek listáját modellező {@link Colors} osztályt.
     */
    public Colors getColors() {
        return colors;
    }

    /**
     * Beállítja a kocsi lehetséges színeit/színét.
     * @param colors A beállítandó színek/szín.
     */
    public void setColors(Colors colors) {
        this.colors = colors;
    }

    /**
     * 
     * @return Visszaadja a kocsi motor típusainak listáját modellezp {@link MotorTypes} osztályt.
     */
    public MotorTypes getMotorTypes() {
        return motorTypes;
    }

    /**
     * Beállítja a lehetséges motor típusokat/típust.
     * @param motorTypes A beállítandó motor típus/típusok.
     */
    public void setMotorTypes(MotorTypes motorTypes) {
        this.motorTypes = motorTypes;
    }

    /**
     *
     * @return Visszaadja az autó szintjét.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Beállítja az autó szintjét.
     * @param level A beállítandó autó szint.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return name;
    }
    /**
     * A {@link com.buba.thecarsalonmaven.controllers.CarConfiguratorController} label-jéhez állít elő {@link String}-et.
     * @return Visszatér egy az objektumot leíró {@link String}-el.
     */
    public String toLabelString(){
        return "Név: "+name+"\nMotor típusok: "+motorTypes+"\nSzínek: "+colors;
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
        final ConfCar other = (ConfCar) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.cost != other.cost) {
            return false;
        }
        if (this.level != other.level) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.colors, other.colors)) {
            return false;
        }
        if (!Objects.equals(this.motorTypes, other.motorTypes)) {
            return false;
        }
        return true;
    }
    
    
}