package com.buba.thecarsalonmaven.models;

import com.buba.thecarsalonmaven.LocalDateAdapter;
import java.time.LocalDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Egy rendelést modellező osztály.
 * 
 * @author Szanics Levente
 */
@XmlRootElement(name = "order")
@XmlAccessorType (XmlAccessType.FIELD)
public class Order {
    private ConfCar confCar;
    private Parts parts;
    private int cost;
    private String user;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate date;

    /**
     * Az {@link Order} osztály üres kostruktora.
     */
    public Order() {
        super();
    }
    
    /**
     * Az {@link Order} osztály konstruktora az összes taggal.
     * 
     * @param confCar egy {@link ConfCar} típusú objektum, ami a rendelt kocsi.
     * @param parts egy {@link Parts} típusú objektum, ami tartalmazza a rendelt kocsihoz tartozó extrákat.
     * @param cost egy {@link int} típusú változó, ami tartalmazza a rendelt kocsi teljes árát. 
     * @param user egy {@link String} típusú objektum, ami a rendelő {@link User} username-e.
     * @param date egy {@link LocalDate} típusú objektum, ami a rendelés idejét tárolja.
     */
    public Order(ConfCar confCar,Parts parts, int cost, String user, LocalDate date) {
        this.confCar = confCar;
        this.parts = parts;
        this.cost = cost;
        this.user = user;
        this.date = date;
    }
    
    @Override
    public String toString() {
        return "Típus: "+confCar.getName()+", Dátum: "+date.toString();
    }

    /**
     * A rendelt kocsi extráit tartalmazó listát tartalmazó objektumot adja vissza.
     * @return Visszatér egy {@link Parts} típusú extrákat ({@link Part}) tartalmazó listát tartalmazó objektummal.
     */
    public Parts getParts() {
        return parts;
    }

    /**
     * Beállítja a rendelt kocsihoz tartozó extrákat.
     * @param parts egy {@link Part}-okat tartalmazó listát tartalmazó {@link Parts} objektum
     */
    public void setParts(Parts parts) {
        this.parts = parts;
    }

    /**
     * A rendelt kocsit adja vissza.
     * @return Visszatér egy {@link ConfCar} típusú objektummal.
     */
    public ConfCar getCar() {
        return confCar;
    }

    /**
     * Beállítja a rendelt kocsit.
     * @param confCar egy {@link ConfCar} típusú objektum.
     */
    public void setCar(ConfCar confCar) {
        this.confCar = confCar;
    }

    /**
     * A rendelt kocsi árát adja vissza.
     * @return Visszatér egy {@link int} típusú változóval
     */
    public int getCost() {
        return cost;
    }

    /**
     * Beállítja a rendelt autó árát.
     * @param cost egy {@link int} típusú változó.
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * A rendelő {@link User} felhasználónevét adja vissza.
     * @return Visszatér egy {@link String} típusú objektummal, amegy a rendelő felhasználóneve.
     */
    public String getUser() {
        return user;
    }

    /**
     * Beállítja a rendelő felhasználónevét.
     * @param user egy {@link String} típusú objektum, amely a rendelő felhasználóneve.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * A rendelés dátumát adja vissza.
     * @return Visszatér egy {@link LocalDate} típusú objektummal, amely a rendelés dátuma.
     */
    public LocalDate getOrderDate() {
        return date;
    }

    /**
     * Beállítja a rendelés dátumát.
     * @param date egy {@link Localdate} típusú objektum, amely a rendelés dátuma.
     */
    public void setOrderDate(LocalDate date) {
        this.date = date;
    }
    
    
}
