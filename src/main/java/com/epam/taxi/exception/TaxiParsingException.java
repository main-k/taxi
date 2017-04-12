package com.epam.taxi.exception;

public class TaxiParsingException extends Exception {
    public TaxiParsingException() {
        super();
    }

    public TaxiParsingException(String message) {
        super(message);
    }

    public TaxiParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaxiParsingException(Throwable cause) {
        super(cause);
    }
}
