package com.gogoflyapp.gogofly.tools;

import java.util.ArrayList;

/**
 * Created by Kees on 12/10/2016.
 *
 * Singleton to store data
 */
public class Suitcase {
    private static Suitcase ourInstance = new Suitcase();

    public static Suitcase getInstance() {
        return ourInstance;
    }

    private Suitcase() {
    }

    // Data
    private ArrayList<Flight> flights = new ArrayList<>();


    // Setter & Getters
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }
}
