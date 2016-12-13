package com.gogoflyapp.gogofly.tools;

/**
 * Created by Kees Marijs on 12/12/2016.
 *
 * Should help with showing the Situation at the other end of the flight.
 */

public class Weather {
    public enum Sky {
        SUN, OVERCAST, RAIN, MIST, SNOW
    }

    private int city_weather_code;
    private double temperature;
    private Sky sky_state;

    public Weather(int city_weather_code, double temperature, Sky sky_state) {
        this.city_weather_code = city_weather_code;
        this.temperature = temperature;
        this.sky_state = sky_state;
    }

    public int getCity_weather_code() {
        return city_weather_code;
    }

    public void setCity_weather_code(int city_weather_code) {
        this.city_weather_code = city_weather_code;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Sky getSky_state() {
        return sky_state;
    }

    public void setSky_state(Sky sky_state) {
        this.sky_state = sky_state;
    }
}
