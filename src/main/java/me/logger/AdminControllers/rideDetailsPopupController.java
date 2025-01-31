package me.logger.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import me.logger.Utility.GeneralObjects.Rides;

public class rideDetailsPopupController {

    @FXML
    private Label topLevel;
    @FXML
    private TextField tf_rideName;
    @FXML
    private TextField tf_status;
    @FXML
    private TextField tf_minHeight;
    @FXML
    private TextField tf_capacity;
    @FXML
    private TextField tf_price;

    public void setRideDetails(Rides ride) {
        topLevel.setText(ride.getRideName());
        tf_rideName.setText(ride.getRideName());
        tf_status.setText(ride.getStatus());
        tf_minHeight.setText(ride.getMinHeight());
        tf_capacity.setText(String.valueOf(ride.getCapacity()));
        tf_price.setText(String.valueOf(ride.getTicketPrice()));
    }

}
