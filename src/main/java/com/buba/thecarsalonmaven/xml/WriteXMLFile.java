package com.buba.thecarsalonmaven.xml;

import com.buba.thecarsalonmaven.models.Orders;
import com.buba.thecarsalonmaven.models.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.slf4j.LoggerFactory;

/**
 * Ez az osztály a JaXB-hez hoz létre {@link JAXBContext}-t és {@link Marshaller}-t.
 * @author Szanics Levente
 */
public class WriteXMLFile {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(WriteXMLFile.class);
    private JAXBContext jaxbContext;
    private Marshaller jaxbMarshaller;

    /**
     * Üres konstruktor a {@link WriteXMLFile} osztályhoz.
     */
    public WriteXMLFile() {
        
    }
    
    /**
     * Létrehozza a {@link JAXBContext}-t és a {@link Marshaller}-t a {@link Users} és az {@link Orders} osztályhoz.
     * @param c Ha 0, akkor a {@link Users} oszályhoz, ha 1, akkor az {@link Orders} osztályhoz hozza létre a {@link JAXBContext}-t.
     */
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
            logger.error(ex.getClass().getName() + ": Marshaller létrehozási hiba.");
        }
    }
    
    /**
     *
     * @return Az osztályhoz tartozó {@link JAXBContext}
     */
    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

    /**
     *
     * @return Az osztályhoz tartozó {@link Marshaller}
     */
    public Marshaller getJaxbMarshaller() {
        return jaxbMarshaller;
    }

}
