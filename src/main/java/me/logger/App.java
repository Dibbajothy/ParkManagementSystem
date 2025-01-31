package me.logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.StringPaths.resourceDictionary;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxmlDictionary.employee.EmployeeLogin));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("LogIn!");
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream(resourceDictionary.pictures.micky))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}