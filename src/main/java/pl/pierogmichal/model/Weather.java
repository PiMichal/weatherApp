package pl.pierogmichal.model;

import java.util.ArrayList;

public class Weather {
    private ArrayList<Forecast> forecasts;

    public Weather(ArrayList<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public ArrayList<Forecast> getForecasts() {
        return forecasts;
    }
}
