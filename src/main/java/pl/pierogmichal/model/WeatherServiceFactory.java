package pl.pierogmichal.model;

import org.springframework.web.client.RestTemplate;
import pl.pierogmichal.model.client.ForecastClient;
import pl.pierogmichal.model.client.WeatherClient;

public class WeatherServiceFactory {
    public static WeatherService createWeatherService() {

        return new WeatherService(createWeatherClient(new RestTemplate()));
    }

    public static WeatherClient createWeatherClient(RestTemplate restTemplate) {
        return new ForecastClient(restTemplate);
    }
}
