package com.epam.taxi.entity;

import com.epam.taxi.district.District;
import com.epam.taxi.idcounter.TaxiIdCounter;

import static java.lang.Math.abs;

public class Taxi  {
    private int id;
    private District currentLocation;

    public Taxi(District currentLocation) {
        id = TaxiIdCounter.incrementId();
        this.currentLocation = currentLocation;
    }

    public District getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(District currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Taxi{" +
                "id = " + id +
                ", currentLocation = " + currentLocation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Taxi taxi = (Taxi) o;

        if (id != taxi.id) return false;
        return getCurrentLocation() == taxi.getCurrentLocation();

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (getCurrentLocation() != null ? getCurrentLocation().hashCode() : 0);
        return result;
    }
}
