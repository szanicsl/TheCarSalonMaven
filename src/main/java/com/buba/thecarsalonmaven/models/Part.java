package com.buba.thecarsalonmaven.models;

import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Egy extrát modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "part")
@XmlAccessorType (XmlAccessType.FIELD)
public class Part {

    private String pname;
    private String plevel;
    private String pcost;

    /**
     * Üres konstruktora a {@link Part} osztálynak.
     */
    public Part() {
    }

    /**
     * Paraméteres konstruktora a {@link Part} osztálynak.
     * @param name Az extra neve.
     * @param level Az extra szintje.
     * @param cost Az extra ára.
     */
    public Part(String name, String level, String cost) {
        this.pname = name;
        this.plevel = level;
        this.pcost = cost;
    }

    @Override
    public String toString() {
        return pname;
    }

    /**
     *
     * @return Az extra neve.
     */
    public String getPname() {
        return pname;
    }

    /**
     *
     * @return Az extra szintje.
     */
    public String getPlevel() {
        return plevel;
    }

    /**
     *
     * @return Az extra ára.
     */
    public String getPcost() {
        return pcost;
    }

    /**
     * Beállítja az extra nevét.
     * @param name Az extra neve.
     */
    public void setName(String name) {
        this.pname = name;
    }

    /**
     * Beállítja az extra szintjét.
     * @param level Az extra szintje.
     */
    public void setLevel(String level) {
        this.plevel = level;
    }

    /**
     * Beállítja az extra árát.
     * @param cost Az extra ára.
     */
    public void setCost(String cost) {
        this.pcost = cost;
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
        final Part other = (Part) obj;
        if (!Objects.equals(this.pname, other.pname)) {
            return false;
        }
        if (!Objects.equals(this.plevel, other.plevel)) {
            return false;
        }
        if (!Objects.equals(this.pcost, other.pcost)) {
            return false;
        }
        return true;
    }
    
    
}
