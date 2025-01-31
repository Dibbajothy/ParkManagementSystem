package me.logger.Utility.HandleServer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.logger.AdminControllers.rideDetailsPopupController;
import me.logger.AdminControllers.updateRidePopUpController;
import me.logger.Utility.CustomAlerts.warningAlert;
import me.logger.Utility.GeneralObjects.Rides;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.StringPaths.serverCred;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RideService {


    public static void setupRideTable(TableView<Rides> table,
                                      TableColumn<Rides, String> rideName,
                                      TableColumn<Rides, String> minHeight,
                                      TableColumn<Rides, String> status,
                                      TableColumn<Rides, Integer> capacity,
                                      TableColumn<Rides, Integer> ticketPrice
    )
    {
        rideName.setCellValueFactory(new PropertyValueFactory<>("rideName"));
        minHeight.setCellValueFactory(new PropertyValueFactory<>("minHeight"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        ticketPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
    }


    public static ObservableList<String> getRunningRides() {
        ObservableList<String> rideNames = FXCollections.observableArrayList();
        String query = serverCred.selectRunningRides;

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password);
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                rideNames.add(resultSet.getString("rideName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rideNames;
    }

    public static ObservableList<Rides> getFullRidesList() {
        ObservableList<Rides> ridesList = FXCollections.observableArrayList();
        String query = serverCred.selectAllFromRides;

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {
            if (connection != null) {
                try (ResultSet resultSet = connection.prepareStatement(query).executeQuery()) {

                    while (resultSet.next()) {
                        ridesList.add(new Rides(
                                resultSet.getString("rideName"),
                                resultSet.getString("minHeight"),
                                resultSet.getInt("capacity"),
                                resultSet.getString("status"),
                                resultSet.getInt("ticketPrice")
                        ));
                    }
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ridesList;
    }


    public static void showDetails(TableView<Rides> table_rides) {
        Rides selectedRide = table_rides.getSelectionModel().getSelectedItem();

        if (selectedRide != null) {
            try {
                FXMLLoader loader = new FXMLLoader(RideService.class.getResource(fxmlDictionary.admin.ridePopUpView));
                Parent root = loader.load();

                // Get the controller for the popup
                rideDetailsPopupController popupController = loader.getController();

                // Set the employee details in the popup
                popupController.setRideDetails(selectedRide);

                // Create and show the popup stage
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setTitle("Ride Details");
                popupStage.setScene(new Scene(root));
                popupStage.setResizable(false);
                popupStage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            warningAlert.showWarningAlert("No Ride selected", "Please select a Ride from the table to view details.");
        }
    }

    public static void updateRide(TableView<Rides> table_rides) {
        Rides selectedRide = table_rides.getSelectionModel().getSelectedItem();

        if (selectedRide != null) {
            try {
                FXMLLoader loader = new FXMLLoader(RideService.class.getResource(fxmlDictionary.admin.updateRidePopUp));
                Parent root = loader.load();

                // Get the controller for the popup
                updateRidePopUpController popupController = loader.getController();
                popupController.setRideDetails(selectedRide);

                // Show the popup
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setTitle("Update Ride");
                popupStage.setScene(new Scene(root));
                popupStage.setResizable(false);
                popupStage.showAndWait();

                // Refresh the table after updating
                reloadRideData(table_rides);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            warningAlert.showWarningAlert("No Ride Selected", "Please select a Ride to update.");
        }
    }

    public static void reloadRideData(TableView<Rides> table_rides) {
        table_rides.setItems(RideService.getFullRidesList());
        table_rides.refresh();
    }


}

