/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buba.thecarsalonmaven.xml;

import com.buba.thecarsalonmaven.models.Order;
import com.buba.thecarsalonmaven.models.Orders;
import com.buba.thecarsalonmaven.models.User;
import com.buba.thecarsalonmaven.models.Users;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WriteXMLFile {

    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;

    public WriteXMLFile() {
        
    }
    
    public void init(int c){
        
        try {
            if(c==0){
                this.jaxbContext = JAXBContext.newInstance(Users.class);
            }else if(c==1){
                this.jaxbContext = JAXBContext.newInstance(Orders.class);
            }
            this.jaxbMarshaller = jaxbContext.createMarshaller();
            this.jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException ex) {
            System.out.println("HIBA");
        }
    }
    
    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    public Marshaller getJaxbMarshaller() {
        return jaxbMarshaller;
    }

}
