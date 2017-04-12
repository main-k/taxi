package com.epam.taxi.district;

import static java.lang.Math.abs;

public enum District {
    DISTRICT0(0), DISTRICT1(1), DISTRICT2(2), DISTRICT3(3), DISTRICT4(4);

    private int id;

    District(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static int getDistance(District d1, District d2){
        return abs(d1.getId() - d2.getId());
    }
}
