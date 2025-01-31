package me.logger.Utility.WorkOfView;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class sceneControl {

    public static void changeScene(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(sceneControl.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 1000, 650));
            stage.show();

        } catch (IOException e) {
            System.err.println("Error loading the FXML file: " + fxmlFile);
            e.printStackTrace();

        } catch (ClassCastException e) {
            System.err.println("Error casting the event source to a Node.");
            e.printStackTrace();
        }
    }
}
