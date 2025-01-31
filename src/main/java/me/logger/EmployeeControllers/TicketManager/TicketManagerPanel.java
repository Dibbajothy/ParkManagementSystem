package me.logger.EmployeeControllers.TicketManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.WorkOfView.sceneControl;

import java.net.URL;
import java.util.ResourceBundle;



public class TicketManagerPanel implements Initializable {

    @FXML
    private BorderPane TicketManagerBorderPane;
    @FXML
    private Button button_logout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadView(fxmlDictionary.employee.TicketManagerDashboard);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneControl.changeScene(event, fxmlDictionary.employee.EmployeeLogin, "Employee Login");
            }
        });
    }

    private void loadView(String fxmlFile) {
        try {
            if(fxmlFile != null && TicketManagerBorderPane != null) {
                Pane view = FXMLLoader.load(getClass().getResource(fxmlFile));
                TicketManagerBorderPane.setCenter(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void createTicket() {
        loadView(fxmlDictionary.employee.ticketCreating);
    }
    @FXML
    private void checkTicket() {
        loadView(fxmlDictionary.employee.ticketChecking);
    }
    @FXML
    private void dashboard() {loadView(fxmlDictionary.employee.TicketManagerDashboard);}


}
