package pl.pierogmichal.model.client;

import pl.pierogmichal.model.Weather;

public interface WeatherClient {
    Weather getWeather(String currentCity);
}
