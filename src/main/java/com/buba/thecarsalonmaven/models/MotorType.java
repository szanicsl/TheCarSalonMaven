package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * A motor típusát modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "motortype")
@XmlAccessorType (XmlAccessType.FIELD)
public class MotorType {
    private String mName;
    private MotorSizes motorSizes;

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
     * Paraméteres konstruktora a {@link MotorType} osztálynak.
     * @param mName A motor típus neve.
     * @param motorSizes A motor méretét, lehetséges méreteit és árát visszaadó {@link MotorSizes} típusú objektum.
     */
    public MotorType(String mName, MotorSizes motorSizes) {
        this.mName = mName;
        this.motorSizes = motorSizes;
    }

    /**
     *
     * @return A motor típus neve.
     */
    public String getMname() {
        return mName;
    }

    /**
     * Beállítja a motor típus nevét.
     * @param mName A motor típus neve.
     */
    public void setMname(String mName) {
        this.mName = mName;
    }

    /**
     *
     * @return A motor méretét, lehetséges méreteit és árát visszaadó {@link MotorSizes} típusú objektum.
     */
    public MotorSizes getMotorSizes() {
        return motorSizes;
    }

    /**
     * Beállítja a motor méretét, lehetséges méreteit és árát visszaadó {@link MotorSizes} típusú objektumot.
     * @param motorSizes A motor méretét, lehetséges méreteit és árát visszaadó {@link MotorSizes} típusú objektum.
     */
    public void setMotorSizes(MotorSizes motorSizes) {
        this.motorSizes = motorSizes;
    }

    @Override
    public String toString() {
        return mName;
    }
    
}
