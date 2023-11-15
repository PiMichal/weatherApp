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
    private TextField currentCity, planingCity;
    @FXML
    private Label currentCountryCity, errorLabel, planingCountryCity;
    @FXML
    private TableView<Forecast> tableView, tableView2;
    @FXML
    private TableColumn<Forecast, String> dateTimeColumn, dateTimeColumn2, descriptionColumn, descriptionColumn2,
            pressureColumn, pressureColumn2, temperatureColumn, temperatureColumn2;


    @FXML
    void showWeatherAction() {

        if (fieldsAreValid()) {

            Weather firstWeather = weatherService.getWeather(currentCity.getText());
            Weather secondWeather = weatherService.getWeather(planingCity.getText());

            if (firstWeather != null && secondWeather != null) {
                currentCountryCity.setText(firstWeather.getCountry() + ", " + currentCity.getText());
                planingCountryCity.setText(secondWeather.getCountry() + ", " + planingCity.getText());

                ObservableList<Forecast> firstCityForecasts = FXCollections.observableArrayList(firstWeather.getForecasts());
                ObservableList<Forecast> secondCityForecasts = FXCollections.observableArrayList(secondWeather.getForecasts());

                tableView.setItems(firstCityForecasts);
                tableView2.setItems(secondCityForecasts);

                tableView.setVisible(true);
                tableView2.setVisible(true);
                errorLabel.setText("");
            } else {
                tableView.setVisible(false);
                tableView2.setVisible(false);
                currentCountryCity.setText("");
                planingCountryCity.setText("");
                errorLabel.setText("Please enter correct data.");
            }

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        weatherService = WeatherServiceFactory.createWeatherService();

        setupTableView(tableView);
        setupTableView2(tableView2);
    }
    private boolean fieldsAreValid() {
        if (currentCity.getText().isEmpty() || planingCity.getText().isEmpty()) {
            errorLabel.setText("Please fill City.");
            return false;
        } else {
            return true;
        }
    }
    private void setupTableView(TableView<Forecast> tableView) {

        dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        pressureColumn.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        temperatureColumn.setCellValueFactory(new PropertyValueFactory<>("temperature"));
    }
    private void setupTableView2(TableView<Forecast> tableView) {
        dateTimeColumn2.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        descriptionColumn2.setCellValueFactory(new PropertyValueFactory<>("description"));
        pressureColumn2.setCellValueFactory(new PropertyValueFactory<>("pressure"));
        temperatureColumn2.setCellValueFactory(new PropertyValueFactory<>("temperature"));
    }

}
