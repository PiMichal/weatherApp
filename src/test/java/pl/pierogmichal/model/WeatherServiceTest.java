package pl.pierogmichal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.pierogmichal.model.client.WeatherClient;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class WeatherServiceTest {

    @InjectMocks
    private WeatherService weatherService;
    @Mock
    private WeatherClient weatherClient;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnWeatherForecast() {
        // given
        when(weatherClient.getWeather("Alaska")).thenReturn(createMockWeather());

        // when
        Weather weather = weatherService.getWeather("Alaska");

        //Then
        assertNotNull(weather, "Weather should not be null");
        assertNotNull(weather.getForecasts(), "Forecasts should not be null");
        assertFalse(weather.getForecasts().isEmpty(), "Forecasts should not be empty");
    }

    @Test
    void shouldReturnNull() {
        // given
        when(weatherClient.getWeather("Alaska")).thenReturn(null);

        // when
        Weather weather = weatherService.getWeather("");

        //Then
        assertNull(weather, "Weather should be null");
    }

    private Weather createMockWeather() {

        ArrayList<Forecast> forecasts = new ArrayList<>();
        forecasts.add(new Forecast("2023-01-01 12:00:00", "Sunny", -14, 1004.0));
        forecasts.add(new Forecast("2023-01-02 12:00:00", "Cloudy", -23, 1015.0));

        return new Weather("Alaska", forecasts);
    }

}