package com.buba.thecarsalonmaven.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.slf4j.LoggerFactory;

/**
 * JaXB számára készült Adapter osztály, mely a {@link LocalDate}-{@link String} közti konvertálásokat végzi el.
 * @author Szanics Levente
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LocalDateAdapter.class);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        logger.debug("String LocalDate-é alakítva.");
        return LocalDate.parse(v,formatter);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        logger.debug("LocalDate String-é alakítva.");
        return v.toString();
    }
}