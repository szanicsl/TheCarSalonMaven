package com.buba.thecarsalonmaven.handlers;

import com.buba.thecarsalonmaven.models.Part;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

/**
 * Egy handler osztály, amely a SAX működéséhez szükséges. A {@link Part} objektumokat hivatott előállítani egy xml fájlból.
 * @author szani
 */
public class PartHandler extends DefaultHandler {

    private final List<Part> partList = new ArrayList<Part>();
    private Part part;

    private boolean pname;
    private boolean plevel;
    private boolean pcost;

    /**
     *
     * @return Visszaadja az osztály egy példányában előállított {@link Part} listáját.
     */
    public List<Part> getPartList() {
        return partList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("part")) {
            part = new Part();
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
        if (qName.equalsIgnoreCase("part")) {
            partList.add(part);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException, NullPointerException{
        if (pname) {
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

