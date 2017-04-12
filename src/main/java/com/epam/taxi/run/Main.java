package com.epam.taxi.run;

import com.epam.taxi.district.District;
import com.epam.taxi.entity.Client;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Client> clients = new ArrayList<Client>() {{
            add(new Client(District.DISTRICT0, District.DISTRICT1));
            add(new Client(District.DISTRICT0, District.DISTRICT4));
            add(new Client(District.DISTRICT3, District.DISTRICT2));
            add(new Client(District.DISTRICT1, District.DISTRICT3));
            add(new Client(District.DISTRICT2, District.DISTRICT0));
            add(new Client(District.DISTRICT4, District.DISTRICT1));
        }};
        for (Client client : clients) {
            client.start();
        }
    }
}
