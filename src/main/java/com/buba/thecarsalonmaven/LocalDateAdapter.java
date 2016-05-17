package com.buba.thecarsalonmaven;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Szanics Levente
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v,formatter);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}