module WeatherApp {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires com.google.gson;
    requires spring.web;

    opens pl.pierogmichal;
    opens pl.pierogmichal.controller;
    opens pl.pierogmichal.model;
    opens pl.pierogmichal.view;
}