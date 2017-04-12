package com.epam.taxi.parser;

import com.epam.taxi.district.District;
import com.epam.taxi.exception.TaxiParsingException;

public class TaxiParser {
    public static District parse(String str) throws TaxiParsingException {
        try {
            District disctrict = District.valueOf(str);
            return disctrict;
        } catch (IllegalArgumentException ex) {
            throw new TaxiParsingException("Incorrect taxi data: " + ex);
        }
    }
}
