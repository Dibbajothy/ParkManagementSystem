package me.logger.Utility.CustomAlerts;

import javafx.scene.control.Alert;

public class warningAlert {
    public static void showWarningAlert(String type, String text) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(type);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
