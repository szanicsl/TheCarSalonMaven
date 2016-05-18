package com.buba.thecarsalonmaven.models;

import java.util.ArrayList;
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
/**
 * A felhasználókat modellező osztály.
 * @author Szanics Levente
 */
@XmlRootElement(name = "users")
@XmlAccessorType (XmlAccessType.FIELD)
public class Users {
    @XmlElement(name = "user")
    private List<User> users = new ArrayList<User>();

    /**
     *
     * @return A felhasználók listája.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Beállítja a felhasználók listáját.
     * @param users A felhasználók listája.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
