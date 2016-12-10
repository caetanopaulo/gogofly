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
    private ArrayList<Flight> totalFlights = new ArrayList<>();
    private ArrayList<Flight> selectedFlights = new ArrayList<>();


    // Setter & Getters
    public ArrayList<Flight> getTotalFlights() {
        return totalFlights;
    }

    public void setTotalFlights(ArrayList<Flight> flights) {
        this.totalFlights = flights;
    }

    public ArrayList<Flight> getSelectedFlights() {
        return selectedFlights;
    }

    public void setSelectedFlights(ArrayList<Flight> selectedFlights) {
        this.selectedFlights = selectedFlights;
    }
}
