package com.epam.taxi.company;

import com.epam.taxi.district.District;
import com.epam.taxi.entity.Taxi;
import com.epam.taxi.factory.TaxiFactory;
import com.epam.taxi.reader.TaxiReader;
import com.epam.taxi.reader.TaxiTextFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiCompany {

    private static TaxiCompany instance;
    private static AtomicBoolean flag = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private List<Taxi> taxis = new ArrayList<>();
    private final String INIT_FILE = "input/input.txt";

    private TaxiCompany() {
        initTaxiCompany();
    }

    public static TaxiCompany getInstance() {
        if (!flag.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new TaxiCompany();
                    flag.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initTaxiCompany() {
        TaxiFactory factory = new TaxiFactory();
        TaxiReader reader = new TaxiTextFileReader();
        Taxi taxi;
        List<String> lines = reader.read(INIT_FILE);
        for (String line : lines) {
            taxi = factory.getTaxi(line);
            if (taxi != null) {
                taxis.add(taxi);
            }
        }
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Taxi taxi : taxis) {
            str.append(taxi);
            str.append("\n");
        }
        return str.toString();
    }

    public Taxi orderTaxi(District whereIsClient) {
        boolean isOrdered = false;
        Taxi taxi = null;
        while (!isOrdered) {
            try {
                lock.lock();
                if (!taxis.isEmpty()) {
                    taxi = taxis.remove(chooseTaxi(whereIsClient));
                    isOrdered = true;
                }
            } finally {
                lock.unlock();
            }
        }
        return taxi;
    }

    public void returnTaxi(Taxi taxi) {
        try {
            lock.lock();
            taxis.add(taxi);
        } finally {
            lock.unlock();
        }
    }

    private int chooseTaxi(District whereIsClient) {
        int taxiIndex = 0;
        int distance;
        int minDistance = 10;
        for (Taxi taxi : taxis) {
            if ((distance = District.getDistance(taxi.getCurrentLocation(), whereIsClient)) < minDistance) {
                minDistance = distance;
                taxiIndex = taxis.indexOf(taxi);
            }
        }
        return taxiIndex;
    }
}
