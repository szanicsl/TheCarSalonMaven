package com.buba.thecarsalonmaven.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 * Egy felhasználót modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "user")
@XmlAccessorType (XmlAccessType.FIELD)
public class User {
    String userName;
    String password;

    /**
     * Üres konstruktora a {@link User} osztálynak.
     */
    public User() {
        super();
    }

    @Override
    public String toString() {
        return "User{" + "userName=" + userName + ", password=" + password;
    }

    /**
     * Paraméteres konstruktora a {@link User} osztálynak.
     * @param userName A felhasználónév.
     * @param password A felhasználó jelszava
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @return A felhasználónév.
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return A felhasználó jelszava.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Beállítja a felhasználónevet.
     * @param userName A felhasználónév
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * Beállítja a felhasználó jelszavát.
     * @param password A felhasználó jelszava.
     */
    public void setPassword(String password) {
        this.password = password;
    } 
    
}
