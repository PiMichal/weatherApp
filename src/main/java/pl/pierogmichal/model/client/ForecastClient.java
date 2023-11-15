package pl.pierogmichal.model.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.pierogmichal.model.Forecast;
import pl.pierogmichal.model.Weather;
import java.util.ArrayList;

public class ForecastClient implements WeatherClient {

    private final RestTemplate restTemplate;

    public ForecastClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Weather getWeather(String currentCity) {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + currentCity + "&appid=" + Config.API_KEY + "&units=metric";

        try {

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                String responseBody = responseEntity.getBody();

                assert responseBody != null;
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                JsonArray list = jsonResponse.getAsJsonArray("list");

                JsonObject city = jsonResponse.getAsJsonObject("city");
                String country = city.get("country").getAsString();


                ArrayList<Forecast> forecasts = new ArrayList<>();

                for (int i = 0; i < list.size(); i++) {
                    JsonObject forecast = list.get(i).getAsJsonObject();
                    String dateTime = forecast.get("dt_txt").getAsString();
                    JsonObject main = forecast.getAsJsonObject("main");
                    JsonArray weatherArray = forecast.getAsJsonArray("weather");
                    String weatherDescription = weatherArray.get(0).getAsJsonObject().get("description").getAsString();
                    double temperature = main.get("temp").getAsDouble();
                    double pressure = main.get("pressure").getAsDouble();

                    Forecast forecastObject = new Forecast(dateTime, weatherDescription, temperature, pressure);
                    forecasts.add(forecastObject);
                }

                return new Weather(country, forecasts);

            } else {
                System.out.println("Error: Unable to retrieve forecast data. Response code: "  + responseEntity.getStatusCodeValue());
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return null;
    }
}

