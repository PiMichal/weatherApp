package pl.pierogmichal.model;

import java.util.ArrayList;

public class Weather {
    private String country;
    private ArrayList<Forecast> forecasts;

    public Weather(String country, ArrayList<Forecast> forecasts) {
        this.country = country;
        this.forecasts = forecasts;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }

    public String getCountry() {
        return country;
    }
}
