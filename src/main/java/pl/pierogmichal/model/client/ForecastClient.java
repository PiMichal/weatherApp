package pl.pierogmichal.model.client;

import pl.pierogmichal.model.Weather;

public class ForecastClient implements WeatherClient{

    @Override
    public Weather getWeather(String currentCity) {

        System.out.println(currentCity);
        System.exit(0);
        return null;
    }
}
