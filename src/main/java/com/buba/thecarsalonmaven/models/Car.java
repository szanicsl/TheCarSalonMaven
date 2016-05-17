package com.buba.thecarsalonmaven.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Szanics Levente
 */
public class Car {

    /**
     * 
     */
    public int id;

    /**
     *
     */
    public StringProperty name;

    /**
     *
     */
    public StringProperty color;

    /**
     *
     */
    public StringProperty motorType;

    /**
     *
     */
    public StringProperty motorSize;

    /**
     *
     */
    public StringProperty doorsNumber;

    /**
     *
     */
    public StringProperty cost;

    /**
     *
     */
    public StringProperty level;

    /**
     *
     */
    public Car() {
        this.id = 0;
        this.name = null;
        this.color = null;
        this.motorType = null;
        this.motorSize = null;
        this.doorsNumber = null;
        this.cost = new SimpleStringProperty("0");
        this.level = new SimpleStringProperty("0");
    }

    /**
     *
     * @param id
     * @param name
     * @param color
     * @param motorType
     * @param motorSize
     * @param doorsNumber
     * @param cost
     * @param level
     */
    public Car(int id, String name, String color, String motorType, String motorSize, String doorsNumber, String cost, String level) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.color = new SimpleStringProperty(color);
        this.motorType = new SimpleStringProperty(motorType);
        this.motorSize = new SimpleStringProperty(motorSize);
        this.doorsNumber = new SimpleStringProperty(doorsNumber);
        this.cost = new SimpleStringProperty(cost);
        this.level = new SimpleStringProperty(level);
    }
    
    /**
     *
     * @return
     */
    public StringProperty getNameProperty(){
        return name;
    }

    /**
     *
     * @return
     */
    public StringProperty getColorProperty(){
        return color;
    }

    /**
     *
     * @return
     */
    public StringProperty getMotorTypeProperty(){
        return motorType;
    }

    /**
     *
     * @return
     */
    public StringProperty getMotorSizeProperty(){
        return motorSize;
    }

    /**
     *
     * @return
     */
    public StringProperty getDoorsNumberProperty(){
        return doorsNumber;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name.get();
    }

    /**
     *
     * @return
     */
    public String getColor() {
        return color.get();
    }

    /**
     *
     * @return
     */
    public String getMotorType() {
        return motorType.get();
    }

    /**
     *
     * @return
     */
    public String getMotorSize() {
        return motorSize.get();
    }

    /**
     *
     * @return
     */
    public String getDoorsNumber() {
        return doorsNumber.get();
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name.set(name);
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color.set(color);
    }

    /**
     *
     * @param motorType
     */
    public void setMotorType(String motorType) {
        this.motorType.set(motorType);
    }

    /**
     *
     * @param motorSize
     */
    public void setMotorSize(String motorSize) {
        this.motorSize.set(motorSize);
    }

    /**
     *
     * @param doorsNumber
     */
    public void setDoorsNumber(String doorsNumber) {
        this.doorsNumber.set(doorsNumber);
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + color + " " + motorType + " " + motorSize + " " + doorsNumber + " " + id;
    }

    /**
     *
     * @return
     */
    public String toLabelString() {
        return "Name:\t\t\t" + name.get() + "\nColor:\t\t\t" + color.get() + "\nMotor Type:\t\t" + motorType.get() + "\nMotor Size:\t\t" + motorSize.get() + "\nDoors Number:\t" + doorsNumber.get();
    }

}
