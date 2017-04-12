package com.epam.taxi.factory;

import com.epam.taxi.entity.Taxi;
import com.epam.taxi.exception.TaxiParsingException;
import com.epam.taxi.parser.TaxiParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TaxiFactory {
    private static final Logger LOGGER = LogManager.getLogger(TaxiFactory.class);

    public Taxi getTaxi(String str) {
        Taxi taxi = null;
        try {
            taxi = new Taxi(TaxiParser.parse(str));
        } catch (TaxiParsingException ex) {
            LOGGER.log(Level.ERROR, "Can not define location: " + ex);
        }
        return taxi;
    }
}
