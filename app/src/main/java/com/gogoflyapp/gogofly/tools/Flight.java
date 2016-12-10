package com.gogoflyapp.gogofly.tools;

/**
 * Created by Kees on 12/10/2016.
 */

public class Flight {
    // Vars
    private String name;
    private String code;
    private String departure_loc;
    private String departure_gate;
    private String departure_time;
    private String arrival_loc;
    private String arrival_gate;
    private String arrival_time;
    private String price;

    // Constructor
    public Flight(){
        super();
    };

    // Country_name
    // Country_code
    // Destination_name
    // Destination_code
    // Departure_date
    // Departure_time
    // Return_date
    // Return_time

    // Price
    // Currency
    // Flight_time
    // Popularity

    // ArrayList themes



    public Flight(String name, String code, String departure_loc, String departure_time, String arrival_loc, String price) {
        this.name = name;
        this.code = code;
        this.departure_loc = departure_loc;
        this.departure_time = departure_time;
        this.arrival_loc = arrival_loc;
        this.price = price;
    }


    // Setters Getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDeparture_loc() {
        return departure_loc;
    }

    public void setDeparture_loc(String departure_loc) {
        this.departure_loc = departure_loc;
    }

    public String getDeparture_gate() {
        return departure_gate;
    }

    public void setDeparture_gate(String departure_gate) {
        this.departure_gate = departure_gate;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_loc() {
        return arrival_loc;
    }

    public void setArrival_loc(String arrival_loc) {
        this.arrival_loc = arrival_loc;
    }

    public String getArrival_gate() {
        return arrival_gate;
    }

    public void setArrival_gate(String arrival_gate) {
        this.arrival_gate = arrival_gate;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
