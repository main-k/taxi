package com.epam.taxi.idcounter;

public class TaxiIdCounter {
    private static int idCounter = 1;

    public TaxiIdCounter() {
    }

    public static int incrementId() {
        return idCounter++;
    }
}
