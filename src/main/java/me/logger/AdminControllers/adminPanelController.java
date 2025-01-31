package me.logger.AdminControllers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.WorkOfView.loadingView;
import me.logger.Utility.WorkOfView.sceneControl;

import java.net.URL;
import java.util.ResourceBundle;

public class adminPanelController implements Initializable {

    @FXML
    public BorderPane adminBorderPane;
    @FXML
    private Button button_logout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadingView.loadView(adminBorderPane, fxmlDictionary.admin.adminDashboard);

        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneControl.changeScene(event, fxmlDictionary.admin.adminLogin, "Admin Login");
            }
        });
    }

    @FXML
    private void employeeTable() {
        loadingView.loadView(adminBorderPane, fxmlDictionary.admin.adminPanelTableView);
    }
    @FXML
    private void ridesTable() {
        loadingView.loadView(adminBorderPane, fxmlDictionary.admin.ridesTableView);
    }
    @FXML
    private void dashboard() {loadingView.loadView(adminBorderPane, fxmlDictionary.admin.adminDashboard);}
    @FXML
    private void revenue() {loadingView.loadView(adminBorderPane, fxmlDictionary.admin.revenue);}

}
