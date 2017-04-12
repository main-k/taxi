package com.epam.taxi.entity;

import com.epam.taxi.company.TaxiCompany;
import com.epam.taxi.district.District;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

public class Client extends Thread {
    private static final Logger LOGGER = LogManager.getLogger(Client.class);
    private District iAmHere;
    private District iWantThere;

    public Client(District iAmHere, District iWantThere) {
        this.iAmHere = iAmHere;
        this.iWantThere = iWantThere;
    }

    public District getIAmHere() {
        return iAmHere;
    }

    public void setIAmHere(District iAmHere) {
        this.iAmHere = iAmHere;
    }

    public District getIWantThere() {
        return iWantThere;
    }

    public void setIWantThere(District iWantThere) {
        this.iWantThere = iWantThere;
    }

    @Override
    public void run() {
        Taxi taxi = null;
        try {
            taxi = TaxiCompany.getInstance().orderTaxi(iAmHere);
            System.out.println("Taxi Client #" + this.getId() + " took taxi #" + taxi.getId());
            int usingTime = abs(this.iAmHere.getId() - taxi.getCurrentLocation().getId()) +
                    abs(this.iAmHere.getId() - this.iWantThere.getId());
            TimeUnit.MILLISECONDS.sleep(usingTime * 100);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Taxi Client #" + this.getId() + " lost ->"
                    + e.getMessage());
        } finally {
            if (taxi != null) {
                LOGGER.log(Level.INFO, "Client #" + this.getId() + " : " + taxi.getId() + " taxi released");
                taxi.setCurrentLocation(iWantThere);
                TaxiCompany.getInstance().returnTaxi(taxi);
            }
        }
    }
}
