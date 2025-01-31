package me.logger.EmployeeControllers.RideManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import me.logger.Utility.HandleServer.RideService;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;
import me.logger.Utility.CustomAlerts.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static me.logger.Utility.StringPaths.serverCred.selectAllFromTicketsByUniqueID;

public class MakeRide implements Initializable {

    @FXML private TextField tf_id;
    @FXML private TextArea validation_result;
    @FXML private ComboBox<String> rideWant;
    @FXML private Button makeRide;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> runningRides = RideService.getRunningRides();
        rideWant.getItems().addAll(runningRides);

        makeRide.setOnAction(event -> checkValidity());
    }


    private void checkValidity() {
        String uniqueID = tf_id.getText().trim();
        String selectedRide = rideWant.getValue();

        if (uniqueID.isEmpty() || selectedRide == null) {
            failureAlert.showFailureAlert("Invalid", "Empty Fields","Please input both fields!");
            return;
        }

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password);
             PreparedStatement preparedStatement = connection.prepareStatement(selectAllFromTicketsByUniqueID)) {

            preparedStatement.setString(1, uniqueID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (!resultSet.next()) {
                    failureAlert.showFailureAlert("Invalid Ticket", "Not Found","Ticket Isn't Valid!");
                    return;
                }

                String assignedRides = resultSet.getString("selected_rides");

                List<String> assignedRideList = Arrays.asList(assignedRides.split(",\\s*"));

                if (assignedRideList.contains(selectedRide)) {
                    validation_result.setText("Validated.Enjoy The Ride!");
                } else {
                    validation_result.setText("This ticket doesn't include this ride.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            failureAlert.showFailureAlert("Failed", "SQL Error", "Please try again!");
        }
    }
}


