package com.buba.thecarsalonmaven.models;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

/**
 *
 * @author szani
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
     *
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }
    
    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    } 
    
}
