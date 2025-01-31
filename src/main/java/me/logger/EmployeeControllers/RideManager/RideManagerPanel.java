package me.logger.EmployeeControllers.RideManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import me.logger.Utility.WorkOfView.*;
import me.logger.Utility.StringPaths.*;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RideManagerPanel implements Initializable {

    @FXML private BorderPane RideManagerBorderPane;
    @FXML private Button button_logout, button_dashboard, button_make_ride, button_ride_maintain;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadingView.loadView(RideManagerBorderPane, fxmlDictionary.employee.MakeRide);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneControl.changeScene(event, fxmlDictionary.employee.EmployeeLogin, "Employee Login");
            }
        });

        button_dashboard.setOnAction(event -> dashboard());
        button_make_ride.setOnAction(event -> makeRide());
        button_ride_maintain.setOnAction(event -> maintainRide());
    }



    @FXML
    private void dashboard() {
        loadingView.loadView(RideManagerBorderPane, fxmlDictionary.employee.RideManagerDashboard);
    }
    @FXML
    private void makeRide() {
        loadingView.loadView(RideManagerBorderPane, fxmlDictionary.employee.MakeRide);
    }
    @FXML
    private void maintainRide() {
        loadingView.loadView(RideManagerBorderPane, fxmlDictionary.employee.MaintainRide);
    }

}
