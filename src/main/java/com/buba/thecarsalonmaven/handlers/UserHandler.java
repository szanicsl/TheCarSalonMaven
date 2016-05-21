package com.buba.thecarsalonmaven.handlers;

import com.buba.thecarsalonmaven.models.User;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

/**
 * Egy handler osztály, amely a SAX működéséhez szükséges. A {@link User} objektumokat hivatott előállítani egy xml fájlból.
 * @author szani
 */
public class UserHandler extends DefaultHandler {

    private final List<User> userList = new ArrayList<User>();
    private User user;

    private boolean userName;
    private boolean password;

    /**
     *
     * @return Visszaadja az osztály egy példányában előállított {@link User} listáját.
     */
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            user = new User();
        } else if (qName.equalsIgnoreCase("username")) {
            userName = true;
        } else if (qName.equalsIgnoreCase("password")) {
            password = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            userList.add(user);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException, NullPointerException{
        if (userName) {
            user.setUserName(new String(ch, start, length));
            userName = false;
        } else if (password) {
            user.setPassword(new String(ch, start, length));
            password = false;
        }
    }

}
