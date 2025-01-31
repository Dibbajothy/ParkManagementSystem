package me.logger.EmployeeControllers;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.logger.Utility.StringPaths.fxmlDictionary;

import java.net.URL;
import java.util.ResourceBundle;

public class employeeLoginController implements Initializable {
    @FXML
    private Button button_login, button_admin;
    @FXML
    private TextField tf_username, tf_password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EmployeeDBUtils.logInEmployee(event, tf_username.getText(), tf_password.getText());
            }
        });

        button_admin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EmployeeDBUtils.changeScene(event, fxmlDictionary.admin.adminLogin, "Admin", null);
            }
        });
    }
}