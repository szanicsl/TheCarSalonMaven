package com.buba.thecarsalonmaven.handlers;

import com.buba.thecarsalonmaven.models.Color;
import com.buba.thecarsalonmaven.models.Colors;
import com.buba.thecarsalonmaven.models.ConfCar;
import com.buba.thecarsalonmaven.models.MotorSize;
import com.buba.thecarsalonmaven.models.MotorSizes;
import com.buba.thecarsalonmaven.models.MotorType;
import com.buba.thecarsalonmaven.models.MotorTypes;
import com.buba.thecarsalonmaven.models.Order;
import com.buba.thecarsalonmaven.models.Part;
import com.buba.thecarsalonmaven.models.Parts;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

/**
 * Egy handler osztály, amely a SAX működéséhez szükséges. A {@link Order} objektumokat hivatott előállítani egy xml fájlból.
 * @author Szanics Levente
 */
public class OrderHandler extends DefaultHandler{
    
    private final List<Order> ordersList = new ArrayList<Order>();

    /**
     *
     * @return Visszaadja az osztály egy példányában előállított {@link Order} listáját.
     */
    public List<Order> getOrdersList() {
        return ordersList;
    }
    private Order order;
    private ConfCar confCar;
    
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate date;
    
    private Parts parts;
    private List<Part> partList = new ArrayList<Part>();
    private Part part;
    
    private MotorTypes motorTypes;
    private List<MotorType> motorTypeList = new ArrayList<MotorType>();
    private MotorType motorType ;
    
    private Colors colors;
    private List<Color> colorList = new ArrayList<Color>();
    private Color color ;
    
    private MotorSizes motorSizes;
    private List<MotorSize> motorSizeList = new ArrayList<MotorSize>();
    private MotorSize motorSize ;
    
    private boolean costBool;
    private boolean userBool;
    private boolean dateBool;
    private boolean nameBool;
    private boolean colorBool;
    private boolean levelBool;
    private boolean motorSizeBool;
    private boolean mNameBool;
    private boolean pname;
    private boolean plevel;
    private boolean pcost;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("order")) {
            order = new Order();
        }else if(qName.equalsIgnoreCase("cost")){
            costBool = true;
        }else if(qName.equalsIgnoreCase("user")){
            userBool = true;
        }else if(qName.equalsIgnoreCase("date")){
            dateBool = true;
        }else if (qName.equalsIgnoreCase("confcar")) {
            confCar = new ConfCar();
            String a = attributes.getValue("cost");
            confCar.setCost(Integer.parseInt(a));
            confCar.setId(Integer.parseInt(attributes.getValue("id")));
        }else if(qName.equalsIgnoreCase("name")){
            nameBool = true;
        }else if(qName.equalsIgnoreCase("colors")){
            colors = new Colors();
        }else if(qName.equalsIgnoreCase("color")){
            color = new Color();
            String a = attributes.getValue("cost");
            color.setCost(Integer.parseInt(a));
            colorBool = true;
        }else if(qName.equalsIgnoreCase("motortypes")){
            motorTypes = new MotorTypes();
        }else if(qName.equalsIgnoreCase("motortype")){
            motorType = new MotorType();
        }else if(qName.equalsIgnoreCase("mname")){
            mNameBool = true;
        }else if(qName.equalsIgnoreCase("motorsizes")){
            motorSizes = new MotorSizes();
        }else if(qName.equalsIgnoreCase("motorsize")){
            motorSize = new MotorSize();
            String a = attributes.getValue("cost");
            motorSize.setCost(Integer.parseInt(a));
            motorSizeBool = true;
        }else if(qName.equalsIgnoreCase("level")){
            levelBool = true;
        }else if (qName.equalsIgnoreCase("part")) {
            part = new Part();
        }else if (qName.equalsIgnoreCase("parts")) {
            parts = new Parts();
        } else if (qName.equalsIgnoreCase("pname")) {
            pname = true;
        } else if (qName.equalsIgnoreCase("plevel")) {
            plevel = true;
        } else if (qName.equalsIgnoreCase("pcost")) {
            pcost = true;
        }
        
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("order")) {
            order.setCar(confCar);
            ordersList.add(order);
        }else if (qName.equalsIgnoreCase("confcar")) {
            confCar.setColors(colors);
            confCar.setMotorTypes(motorTypes);
            
            motorTypeList = new ArrayList<MotorType>();
            colorList = new ArrayList<Color>();
        }else if (qName.equalsIgnoreCase("colors")) {
            colors.setColors(colorList);
        }else if(qName.equalsIgnoreCase("color")){
            colorList.add(color);
        }else if(qName.equalsIgnoreCase("motortypes")){
            motorTypes.setMotorTypes(motorTypeList);
        }else if(qName.equalsIgnoreCase("motortype")){
            motorType.setMotorSizes(motorSizes);
            motorTypeList.add(motorType);
            motorSizeList = new ArrayList<MotorSize>();
        }else if(qName.equalsIgnoreCase("motorsizes")){
            motorSizes.setMotorSizes(motorSizeList);
        }else if(qName.equalsIgnoreCase("motorsize")){
            motorSizeList.add(motorSize);
        }else if (qName.equalsIgnoreCase("part")) {
            partList.add(part);
        }else if (qName.equalsIgnoreCase("parts")){
            parts.setParts(partList);
            order.setParts(parts);
            partList = new ArrayList<Part>();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException, NullPointerException{
        if(costBool){
            order.setCost(Integer.parseInt(new String(ch, start, length)));
            costBool = false;
        }else if(userBool){
            String a = new String(ch, start, length);
            order.setUser(a);
            userBool = false;
        }else if(dateBool){
            String a = new String(ch, start, length);
            date = LocalDate.parse(a, formatter);
            order.setOrderDate(date);
            dateBool=false;
        }else if (nameBool) {
            confCar.setName(new String(ch, start, length));
            nameBool = false;
        } else if (colorBool) {
            color.setColor(new String(ch, start, length));
            colorBool = false;
        } else if (mNameBool) {
            motorType.setMname(new String(ch, start, length));
            mNameBool = false;
        }else if (motorSizeBool) {
            motorSize.setSize(new String(ch, start, length));
            motorSizeBool = false;
        }else if (levelBool) {
            confCar.setLevel(Integer.parseInt(new String(ch, start, length)));
            levelBool = false;
        }else if (pname) {
            part.setName(new String(ch, start, length));
            pname = false;
        } else if (plevel) {
            part.setLevel(new String(ch, start, length));
            plevel = false;
        } else if (pcost) {
            part.setCost(new String(ch, start, length));
            pcost = false;
        }
        
    }

}
