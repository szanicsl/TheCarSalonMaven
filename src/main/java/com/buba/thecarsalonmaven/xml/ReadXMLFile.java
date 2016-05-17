/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.buba.thecarsalonmaven.xml;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.buba.thecarsalonmaven.models.Car;
import com.buba.thecarsalonmaven.models.User;
import java.io.InputStream;
import java.util.stream.Collectors;

/**
 *
 * @author szani
 */
public class ReadXMLFile {
    public ObservableList<Car> carsList = FXCollections.observableArrayList(); 
    public ObservableList<Car> notReadyForSaleCarsList = FXCollections.observableArrayList();
    public ObservableList<Car> readyForSaleCarsList = FXCollections.observableArrayList();
    public void readCars() {
        try {
            InputStream is = this.getClass().getResourceAsStream("/xml/cars.xml");
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("car");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    int countE=0;
                    if(carsList.isEmpty()){
                        addCarToList(Integer.parseInt(eElement.getAttribute("id"))
                                    ,eElement.getElementsByTagName("name").item(0).getTextContent()
                                    ,eElement.getElementsByTagName("color").item(0).getTextContent()
                                    ,eElement.getElementsByTagName("motortype").item(0).getTextContent()
                                    ,eElement.getElementsByTagName("motorsize").item(0).getTextContent()
                                    ,eElement.getElementsByTagName("doors").item(0).getTextContent());
                    }else{
                        for(Car c : carsList){
                            if(c.getId()==Integer.parseInt(eElement.getAttribute("id"))){
                                countE++;
                            }
                        }
                        if(countE==0){
                            addCarToList(Integer.parseInt(eElement.getAttribute("id"))
                                        ,eElement.getElementsByTagName("name").item(0).getTextContent()
                                        ,eElement.getElementsByTagName("color").item(0).getTextContent()
                                        ,eElement.getElementsByTagName("motortype").item(0).getTextContent()
                                        ,eElement.getElementsByTagName("motorsize").item(0).getTextContent()
                                        ,eElement.getElementsByTagName("doors").item(0).getTextContent());
                        }  
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int s = carsList.size();
        for(int i = 0;i<s;i++){
            System.out.println(i+".");
            if("-".equals(carsList.get(0).color.get())){
                System.out.println(i+": -");
                notReadyForSaleCarsList.add(carsList.remove(0));
            }else{
                System.out.println(i+": nem -");
                readyForSaleCarsList.add(carsList.remove(0));
            }
        }
    }

    private void addCarToList(int id,String name,String color,String motorType,String motorSize,String doors){
        carsList.add(new Car(id,name,color,motorType,motorSize,doors,"0","0"));
    }

}
