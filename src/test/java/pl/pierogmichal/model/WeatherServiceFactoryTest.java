package pl.pierogmichal.model;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;
import pl.pierogmichal.model.client.WeatherClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WeatherServiceFactoryTest {

    @Test
    void shouldReturnWeatherServiceWithNotNullWeatherClient() {
        // when
        WeatherService weatherService = WeatherServiceFactory.createWeatherService();

        // then
        assertNotNull(weatherService, "WeatherService should not be null");
        assertNotNull(weatherService.getWeatherClient(), "WeatherClient in WeatherService should not be null");
    }

    @Test
    void shouldReturnNotNullWeatherClient() {
        // when
        WeatherClient weatherClient = WeatherServiceFactory.createWeatherClient(new RestTemplate());

        // then
        assertNotNull(weatherClient, "WeatherClient should not be null");
    }

    @Test
    void shouldReturnWeatherClient() {
        // given
        RestTemplate mockRestTemplate = mock(RestTemplate.class);
        when(mockRestTemplate.getForObject("some_url", String.class)).thenReturn("mocked data");

        // when
        WeatherClient weatherClient = WeatherServiceFactory.createWeatherClient(mockRestTemplate);

        // then
        assertNotNull(weatherClient, "WeatherClient should not be null");
    }

}