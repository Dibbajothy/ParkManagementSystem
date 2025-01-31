package me.logger.AdminControllers;
import javafx.scene.Node;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.GeneralObjects.Rides;
import me.logger.Utility.StringPaths.serverCred;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class createRide implements Initializable {

    @FXML
    private Button createButton;
    @FXML
    private TextField tf_rideName, tf_minHeight, tf_capacity, tf_ticketPrice;
    @FXML
    private ChoiceBox<String> choiceBox_rideStatus;

    private final String[] rideStatus = {"Running", "Stopped", "Maintenance"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox_rideStatus.getItems().addAll(rideStatus);

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(areFieldsFilled()){
                    Rides ride = new Rides(tf_rideName.getText(), tf_minHeight.getText(), Integer.parseInt(tf_capacity.getText()), choiceBox_rideStatus.getValue(), Integer.parseInt(tf_ticketPrice.getText()));
                    createRideInDatabase(event, ride);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }
                else {
                    failureAlert.showFailureAlert("Not Filled Up", "Please fill all the fields", "Fill all the fields");
                }
            }
        });

    }


    private boolean areFieldsFilled() {
        return !tf_rideName.getText().trim().isEmpty() &&
                !tf_minHeight.getText().trim().isEmpty() &&
                !tf_capacity.getText().trim().isEmpty() &&
                !tf_ticketPrice.getText().trim().isEmpty()&&
                choiceBox_rideStatus.getValue() != null;
    }

    private void createRideInDatabase(Event event, Rides ride) {
        try (
                Connection connection = DriverManager.getConnection(serverCred.DBurl, serverCred.username, serverCred.password);
                PreparedStatement psCheckID = connection.prepareStatement(serverCred.selectAllFromRidesByName);
                PreparedStatement psInsert = connection.prepareStatement(serverCred.insertAllToRides)
        ) {
            if (doesRideExist(psCheckID, ride.getRideName())) {
                failureAlert.showFailureAlert("Not Allowed", "Ride Exists", "Create Another Ride");
                return;
            }
            insertRide(psInsert, ride);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean doesRideExist(PreparedStatement psCheckRide, String rideName) throws SQLException {
        psCheckRide.setString(1, rideName);
        try (ResultSet resultSet = psCheckRide.executeQuery()) {
            return resultSet.isBeforeFirst();
        }
    }

    private static void insertRide(PreparedStatement psInsert, Rides ride) throws SQLException {
        psInsert.setString(1, ride.getRideName());
        psInsert.setString(2, ride.getMinHeight());
        psInsert.setString(3, ride.getStatus());
        psInsert.setInt(4, ride.getCapacity());
        psInsert.setInt(5, ride.getTicketPrice());
        psInsert.executeUpdate();
    }


}
