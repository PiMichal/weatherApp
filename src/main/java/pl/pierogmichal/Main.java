package pl.pierogmichal;


import javafx.application.Application;
import javafx.stage.Stage;
import pl.pierogmichal.view.ViewFactory;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainViewWindow();
    }
}