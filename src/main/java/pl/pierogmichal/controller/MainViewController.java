package pl.pierogmichal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.pierogmichal.model.Forecast;
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
    private TableView<Forecast> tableView;
    @FXML
    private TableView<Forecast> tableView2;
    @FXML
    private TableColumn<Forecast, String> dateTimeColumn;
    @FXML
    private TableColumn<Forecast, String> dateTimeColumn2;
    @FXML
    private TableColumn<Forecast, String> descriptionColumn;
    @FXML
    private TableColumn<Forecast, String> descriptionColumn2;
    @FXML
    private TableColumn<Forecast, String> pressureColumn;
    @FXML
    private TableColumn<Forecast, String> pressureColumn2;
    @FXML
    private TableColumn<Forecast, String> temperatureColumn;
    @FXML
    private TableColumn<Forecast, String> temperatureColumn2;
    @FXML
    private TextField planingCity;

    @FXML
    private TextField planingCountry;

    @FXML
    private Label planingCountryCity;

    @FXML
    void showWeatherAction() {
        Weather firstWeather = weatherService.getWeather(currentCity.getText());

        ObservableList<Forecast> firstCityForecasts = FXCollections.observableArrayList(firstWeather.getForecasts());

        tableView.setItems(firstCityForecasts);
        tableView.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        weatherService = WeatherServiceFactory.createWeatherService();

        setupTableView(tableView);
    }
    private void setupTableView(TableView<Forecast> tableView) {

        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        pressureColumn.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
    }
}
