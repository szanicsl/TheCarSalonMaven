package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author szani
 */
@XmlRootElement(name = "orders")
@XmlAccessorType (XmlAccessType.FIELD)
public class Orders {
    
    @XmlElement(name = "order")
    private List<Order> orderList = new ArrayList<Order>();

    /**
     *
     * @return
     */
    public List<Order> getOrderList() {
        return orderList;
    }

    /**
     *
     * @param orderList
     */
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    
    
}
