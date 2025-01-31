package me.logger.Utility.WorkOfView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class loadingView {

    public static void loadView(BorderPane borderPane, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(loadingView.class.getResource(fxmlFile));
            Parent view = loader.load();
            borderPane.setCenter(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
