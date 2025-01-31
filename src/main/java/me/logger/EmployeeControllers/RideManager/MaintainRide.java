package me.logger.EmployeeControllers.RideManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.logger.Utility.CustomTableCell.TableCellStyle;
import me.logger.Utility.GeneralObjects.Rides;
import me.logger.Utility.HandleServer.RideService;

import java.net.URL;
import java.util.ResourceBundle;

public class MaintainRide implements Initializable {

    @FXML Button detailsButton, updateButton;
    @FXML TableView<Rides> table_rides;

    @FXML private TableColumn<Rides, String> table_rideName;
    @FXML private TableColumn<Rides, String> table_minHeight;
    @FXML private TableColumn<Rides, String> table_status;
    @FXML private TableColumn<Rides, Integer> table_capacity;
    @FXML private TableColumn<Rides, Integer> table_ticketPrice;

    ObservableList<Rides> listR;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        RideService.setupRideTable(table_rides, table_rideName, table_minHeight, table_status, table_capacity, table_ticketPrice);

        table_status.setCellFactory(TableCellStyle.getStatusCellFactory());

        listR = RideService.getFullRidesList();
        table_rides.setItems(listR);

        detailsButton.setOnAction(event -> RideService.showDetails(table_rides));
        updateButton.setOnAction(event -> RideService.updateRide(table_rides));

    }
}

