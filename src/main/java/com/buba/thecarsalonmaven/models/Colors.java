/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * Az autó szineit tároló osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "colors")
@XmlAccessorType (XmlAccessType.FIELD)
public class Colors {
    
    @XmlElement(name = "color")
    private List<Color> colors = new ArrayList<Color>();

    /**
     * 
     * @return Visszaadja az autó lehetséges színeinek listáját.
     */
    public List<Color> getColors() {
        return colors;
    }

    /**
     * Beállítja az autó lehetséges színeinek listáját.
     * @param colors A beállítandó lista az autó lehetséges színeire
     */
    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        String string = "";
        for(Color c : colors){
            string = string +", "+ c.toString();
        }
        return string;
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
        final Colors other = (Colors) obj;
        if (!Objects.equals(this.colors, other.colors)) {
            return false;
        }
        return true;
    }
    
    
}
