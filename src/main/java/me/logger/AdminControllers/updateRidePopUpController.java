package me.logger.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.GeneralObjects.Rides;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

import java.sql.Connection;

public class updateRidePopUpController {

    @FXML
    private TextField tf_rideName, tf_minHeight, tf_capacity, tf_ticketPrice;
    @FXML
    private ComboBox<String> choiceBox_rideStatus;
    @FXML
    private Button updateButton;

    private Rides rideToUpdate;

    private final String[] rideStatus = {"Running", "Stopped", "Maintenance"};

    public void initialize() {
        choiceBox_rideStatus.getItems().addAll(rideStatus);

        updateButton.setOnAction(event -> updateRideInDatabase());
    }

    public void setRideDetails(Rides ride) {
        this.rideToUpdate = ride;

        // Populate the fields with existing ride data
        tf_rideName.setText(ride.getRideName());
        tf_minHeight.setText(ride.getMinHeight());
        tf_capacity.setText(String.valueOf(ride.getCapacity()));
        tf_ticketPrice.setText(String.valueOf(ride.getTicketPrice()));
        choiceBox_rideStatus.setValue(ride.getStatus());
    }

    private void updateRideInDatabase() {
        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {
            String updateQuery = serverCred.updateRideByName;

            serverConnect.executeUpdate(connection, updateQuery,
                    tf_rideName.getText(),
                    tf_minHeight.getText(),
                    Integer.parseInt(tf_capacity.getText()),
                    choiceBox_rideStatus.getValue(),
                    Integer.parseInt(tf_ticketPrice.getText()),
                    rideToUpdate.getRideName()
            );

            updateButton.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
            failureAlert.showFailureAlert("Error", "Update Failed", "An error occurred while updating the ride.");
        }
    }
}
