package pl.pierogmichal.model;

public class Forecast {
    public String dateTime;
    public String description;
    public double temperature;
    public double pressure;
    public Forecast(String dateTime, String description, double temperature, double pressure) {
        this.dateTime = dateTime;
        this.description = description;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }
}
