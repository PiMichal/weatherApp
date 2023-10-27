package pl.pierogmichal.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.pierogmichal.controller.FxmlController;
import pl.pierogmichal.controller.MainViewController;

import java.io.IOException;

public class ViewFactory {
    public void showMainViewWindow() {

        MainViewController mainViewController = new MainViewController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FxmlController.fxmlName));
        fxmlLoader.setController(mainViewController);
        Parent parent;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);

        Stage stage = new Stage();
        stage.setTitle("Weather app");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
