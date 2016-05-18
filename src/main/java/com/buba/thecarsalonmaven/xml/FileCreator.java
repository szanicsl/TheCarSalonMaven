/*
 * Copyright (C) 2016 University of Debrecen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.buba.thecarsalonmaven.xml;

import com.buba.thecarsalonmaven.models.User;
import com.buba.thecarsalonmaven.models.Users;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 * A kimeneti és bemeneti xml fájlok létrehozására hivatott osztály.
 * @author Szanics Levente
 */
public class FileCreator {

    /**
     * Üres konstrukrora a {@link FileCreator} osztálynak.
     */
    public FileCreator() {
        init();
    }

    private Users users = new Users();

    /**
     * Létrahozza az adatbázis számára szükséges mappát a home mappán belül, majd létrehozza benne a szükséges xml állományokat, amennyiben nem léteznének.
     */
    public void init() {
        String path = System.getProperty("user.home")+"/.projectdatabase/";
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        List<File> filesList = new ArrayList<File>();
        filesList.add(new File(path+"users.xml"));
        filesList.add(new File(path+"orders.xml"));
        for (File file : filesList) {
            if (!file.exists()) {
                try {
                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    Document doc = docBuilder.newDocument();

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(file);

                    transformer.transform(source, result);

                } catch (ParserConfigurationException | TransformerException pce) {
                    pce.printStackTrace();
                }
                if (file.equals(new File(path+"users.xml"))) {
                    try {
                        WriteXMLFile w = new WriteXMLFile();
                        w.init(0);
                        users.getUsers().add(new User("default", "default"));

                        w.getJaxbMarshaller().marshal(users, new File(path+"users.xml"));
                    } catch (JAXBException ex) {
                        Logger.getLogger(FileCreator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

}