package me.logger.Utility.CustomAlerts;

import javafx.scene.control.Alert;

public class successAlert {
    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
