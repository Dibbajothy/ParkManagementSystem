package me.logger.AdminControllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.logger.Utility.CustomAlerts.*;
import me.logger.Utility.GeneralObjects.Rides;
import me.logger.Utility.HandleServer.RideService;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.StringPaths.serverCred;
import me.logger.Utility.CustomTableCell.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ridesTableViewController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button updateButton;


    @FXML
    private TableView<Rides> table_rides;
    @FXML
    private TableColumn<Rides, String> table_rideName;
    @FXML
    private TableColumn<Rides, String> table_minHeight;
    @FXML
    private TableColumn<Rides, String> table_status;
    @FXML
    private TableColumn<Rides, Integer> table_capacity;
    @FXML
    private TableColumn<Rides, Integer> table_ticketPrice;

    ObservableList<Rides> listR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RideService.setupRideTable(table_rides, table_rideName, table_minHeight, table_status, table_capacity, table_ticketPrice);

        table_status.setCellFactory(TableCellStyle.getStatusCellFactory());

        listR = RideService.getFullRidesList();
        table_rides.setItems(listR);

        deleteButton.setOnAction(event -> deleteSelectedRide());
        detailsButton.setOnAction(event -> RideService.showDetails(table_rides));
        addButton.setOnAction(event -> handleAddRide(table_rides));
        updateButton.setOnAction(event -> RideService.updateRide(table_rides));

    }


    private void deleteSelectedRide() {

        Rides selectedRide = table_rides.getSelectionModel().getSelectedItem();

        if (selectedRide != null) {
            String rideName = selectedRide.getRideName();
            String deleteQuery = serverCred.deleteFromRidesByRideName;

            try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {
                serverConnect.executeUpdate(connection, deleteQuery, rideName);

                RideService.reloadRideData(table_rides);

                successAlert.showSuccessAlert("The ride : " + rideName + " was successfully deleted.");

            } catch (Exception e) {
                e.printStackTrace();

                failureAlert.showFailureAlert("Failed", "Deletion Failed", "An error occurred while trying to delete the ride.");
            }
        } else {
            warningAlert.showWarningAlert("No Ride Selected", "Please select an Ride to delete.");
        }
    }


    @FXML
    private void handleAddRide(TableView<Rides> table_rides) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlDictionary.admin.CreateRidePopUp));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Create Ride");
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);
            popupStage.showAndWait();

            RideService.reloadRideData(table_rides);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
