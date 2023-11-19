package pl.pierogmichal.model;

import pl.pierogmichal.model.client.WeatherClient;

public class WeatherService {

    private final WeatherClient weatherClient;

    public WeatherService(WeatherClient weatherClient) {

        this.weatherClient = weatherClient;
    }
    public Weather getWeather(String currentCity) {

        return weatherClient.getWeather(currentCity);
    }
    public WeatherClient getWeatherClient() {
        return weatherClient;
    }
}
