package pl.pierogmichal.model.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pl.pierogmichal.model.Weather;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastClientTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private ForecastClient forecastClient;

    @Test
    void getWeatherReturnsCorrectWeather() throws IOException {
        // Given
        String jsonResponse = loadJson("weather.json");
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn(jsonResponse);

        // When
        Weather weather = forecastClient.getWeather("someCity");

        // Then
        assertThat(weather, is(notNullValue()));
    }
    @Test
    void getWeatherHandlesIOException() throws IOException {
        // Given
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenAnswer(invocation -> {
                    throw new IOException("Simulated IOException");
                });

        // When
        Weather weather = forecastClient.getWeather("someCity");

        // Then
        assertThat(weather, is(nullValue()));
    }
    @Test
    void getWeatherHandlesEmptyJsonResponse() throws IOException {
        // Given
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenReturn("");

        // When
        Weather weather = forecastClient.getWeather("someCity");

        // Then
        assertThat(weather, is(nullValue()));
    }

    private String loadJson(String fileName) throws IOException {
        Path jsonFilePath = Path.of("src/test/resources", fileName);
        return new String(Files.readAllBytes(jsonFilePath));
    }

}