package com.buba.thecarsalonmaven.handlers;

import com.buba.thecarsalonmaven.models.Color;
import com.buba.thecarsalonmaven.models.Colors;
import com.buba.thecarsalonmaven.models.ConfCar;
import com.buba.thecarsalonmaven.models.MotorSize;
import com.buba.thecarsalonmaven.models.MotorSizes;
import com.buba.thecarsalonmaven.models.MotorType;
import com.buba.thecarsalonmaven.models.MotorTypes;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

/**
 * Egy handler osztály, amely a SAX működéséhez szükséges. A {@link ConfCar} objektumokat hivatott előállítani egy xml fájlból.
 * @author Szanics Levente
 */
public class ConfCarHandler extends DefaultHandler{
    private final List<ConfCar> confCarList = new ArrayList<ConfCar>();
    private ConfCar confCar;
    
    private MotorTypes motorTypes;
    private List<MotorType> motorTypeList = new ArrayList<MotorType>();
    private MotorType motorType ;
    
    private Colors colors;
    private List<Color> colorList = new ArrayList<Color>();
    private Color color ;
    
    private MotorSizes motorSizes;
    private List<MotorSize> motorSizeList = new ArrayList<MotorSize>();
    private MotorSize motorSize ;
    
    /**
     *
     * @return Visszaadja az osztály egy példányában előállított {@link ConfCar} listáját.
     */
    public List<ConfCar> getConfCarList() {
        return confCarList;
    }
    
    private boolean nameBool;
    private boolean colorBool;
    private boolean levelBool;
    private boolean motorSizeBool;
    private boolean mNameBool;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("confcar")) {
            confCar = new ConfCar();
            confCar.setCost(Integer.parseInt(attributes.getValue("cost")));
        }else if(qName.equalsIgnoreCase("name")){
            nameBool = true;
        }else if(qName.equalsIgnoreCase("colors")){
            colors = new Colors();
        }else if(qName.equalsIgnoreCase("color")){
            color = new Color();
            color.setCost(Integer.parseInt(attributes.getValue("cost")));
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
            motorSize.setCost(Integer.parseInt(attributes.getValue("cost")));
            motorSizeBool = true;
        }else if(qName.equalsIgnoreCase("level")){
            levelBool = true;
        }
        
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("confcar")) {
            confCar.setColors(colors);
            confCar.setMotorTypes(motorTypes);
            confCarList.add(confCar);
            
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
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException, NullPointerException{
        if (nameBool) {
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
        }
        
    }
    
}
