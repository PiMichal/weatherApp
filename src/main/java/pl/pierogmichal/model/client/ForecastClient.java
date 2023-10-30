package pl.pierogmichal.model.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import pl.pierogmichal.model.Forecast;
import pl.pierogmichal.model.Weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ForecastClient implements WeatherClient {

    @Override
    public Weather getWeather(String currentCity) {

        String apiUrl = "https://api.openweathermap.org/data/2.5/forecast?q=" + currentCity + "&appid=" + Config.API_KEY + "&units=metric";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
                JsonArray list = jsonResponse.getAsJsonArray("list");

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

                return new Weather(forecasts);

            } else {
                System.out.println("Error: Unable to retrieve forecast data. Response code: " + responseCode);
            }

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}

