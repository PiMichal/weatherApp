package pl.pierogmichal.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {
    @Test
    void testWeatherInitialization() {
        // Given
        String country = "Poland";
        ArrayList<Forecast> forecasts = new ArrayList<>();

        // When
        Weather weather = new Weather(country, forecasts);

        // Then
        assertNotNull(weather, "Weather should not be null");
        assertEquals(country, weather.getCountry(), "Country should match");
        assertEquals(forecasts, weather.getForecasts(), "Forecasts should match");
    }

}