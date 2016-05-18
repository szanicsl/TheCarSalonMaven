package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A rendeléseket modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "orders")
@XmlAccessorType (XmlAccessType.FIELD)
public class Orders {
    
    @XmlElement(name = "order")
    private List<Order> orderList = new ArrayList<Order>();

    /**
     *
     * @return A rendeléseket tartalmazó lista.
     */
    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     * Beállítja a rendeléseket tartalmazó listát.
     * @param orderList A rendeléseket tartalmazó lista.
     */
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    
    
}
