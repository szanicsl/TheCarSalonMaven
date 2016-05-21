package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * Az extrákat modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "parts")
@XmlAccessorType (XmlAccessType.FIELD)
public class Parts {
    @XmlElement(name = "part")
    private List<Part> parts = new ArrayList<Part>();

    /**
     *
     * @return Az extrák listája.
     */
    public List<Part> getParts() {
        return parts;
    }

    /**
     * Beállítja az extrák listáját.
     * @param parts Az extrák listája.
     */
    public void setParts(List<Part> parts) {
        this.parts = parts;
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
        final Parts other = (Parts) obj;
        if (!Objects.equals(this.parts, other.parts)) {
            return false;
        }
        return true;
    }
    
    
}
