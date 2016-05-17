package com.buba.thecarsalonmaven.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author szani
 */
@XmlRootElement(name = "part")
@XmlAccessorType (XmlAccessType.FIELD)
public class Part {

    /**
     *
     */
    public String pname;

    /**
     *
     */
    public String plevel;

    /**
     *
     */
    public String pcost;

    /**
     * Üres konstruktora a {@link Part} osztálynak.
     */
    public Part() {
    }

    /**
     *
     * @param name
     * @param level
     * @param cost
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
     * @return
     */
    public String getPname() {
        return pname;
    }

    /**
     *
     * @return
     */
    public String getPlevel() {
        return plevel;
    }

    /**
     *
     * @return
     */
    public String getPcost() {
        return pcost;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.pname = name;
    }

    /**
     *
     * @param level
     */
    public void setLevel(String level) {
        this.plevel = level;
    }

    /**
     *
     * @param cost
     */
    public void setCost(String cost) {
        this.pcost = cost;
    }
}
