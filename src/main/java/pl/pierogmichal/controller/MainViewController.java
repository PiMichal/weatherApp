package pl.pierogmichal.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.pierogmichal.model.Weather;
import pl.pierogmichal.model.WeatherService;
import pl.pierogmichal.model.WeatherServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    private WeatherService weatherService;
    @FXML
    private TextField currentCity;

    @FXML
    private TextField currentCountry;

    @FXML
    private Label currentCountryCity;

    @FXML
    private Label errorLabel;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableView<?> tableView2;
    @FXML
    private TableColumn<?, ?> dateTimeColumn;
    @FXML
    private TableColumn<?, ?> dateTimeColumn2;
    @FXML
    private TableColumn<?, ?> descriptionColumn;
    @FXML
    private TableColumn<?, ?> descriptionColumn2;
    @FXML
    private TableColumn<?, ?> pressureColumn;
    @FXML
    private TableColumn<?, ?> pressureColumn2;
    @FXML
    private TableColumn<?, ?> temperatureColumn;
    @FXML
    private TableColumn<?, ?> temperatureColumn2;
    @FXML
    private TextField planingCity;

    @FXML
    private TextField planingCountry;

    @FXML
    private Label planingCountryCity;

    @FXML
    void showWeatherAction() {
        Weather firstWeather = weatherService.getWeather(currentCity.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weatherService = WeatherServiceFactory.createWeatherService();
    }
}
