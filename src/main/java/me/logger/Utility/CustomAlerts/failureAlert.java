package me.logger.Utility.CustomAlerts;

import javafx.scene.control.Alert;

public class failureAlert {
    public static void showFailureAlert(String errorType, String errorHeader, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorType);
        alert.setHeaderText(errorHeader);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
