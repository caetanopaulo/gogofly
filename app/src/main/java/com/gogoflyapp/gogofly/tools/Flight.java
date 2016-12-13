package com.gogoflyapp.gogofly.tools;

import java.util.ArrayList;

/**
 * Created by Kees on 12/10/2016.
 */

public class Flight {
    // Vars
    private String origin_name;
    private String origin_code;
    private String country_name;
    private String country_code;
    private String destination_name;
    private String destination_code;
    private String departure_date;
    private String departure_time;
    private String return_date;
    private String return_time;

    private String price;
    private String currency;
    private String flight_time;
    private String popularity;

    private ArrayList<Theme> themes;

    private Weather destination_weather;

    // Constructor
    public Flight(){
        super();
    };

    public Flight(String destination_name) {
        this();
        this.setDestination_name(destination_name);
        this.themes = new ArrayList<>();
        destination_weather = null;
    }

    // Setter and Getters
    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public void setDestination_name(String destination_name) {
        this.destination_name = destination_name;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getReturn_time() {
        return return_time;
    }

    public void setReturn_time(String return_time) {
        this.return_time = return_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlight_time() {
        return flight_time;
    }

    public void setFlight_time(String flight_time) {
        this.flight_time = flight_time;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public ArrayList<Theme> getThemes() {
        return themes;
    }

    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }

    public String getOrigin_code() {
        return origin_code;
    }

    public void setOrigin_code(String origin_code) {
        this.origin_code = origin_code;
    }

    public Weather getDestination_weather() {
        return destination_weather;
    }

    public void setDestination_weather(Weather destination_weather) {
        this.destination_weather = destination_weather;
    }
}
