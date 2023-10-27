package pl.pierogmichal.model;

import pl.pierogmichal.model.client.ForecastClient;
import pl.pierogmichal.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {

        return new WeatherService(createWeatherClient());
    }

    public static WeatherClient createWeatherClient() {
        return new ForecastClient();
    }
}
