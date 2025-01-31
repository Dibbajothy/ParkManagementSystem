package me.logger.AdminControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.CustomAlerts.warningAlert;
import me.logger.Utility.GeneralObjects.Employee;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class employeeUpdatePopup implements Initializable {

    @FXML
    private TextField tf_employeeid, tf_name, tf_email, tf_address, tf_phone, tf_salary, tf_username, tf_password;
    @FXML
    private ComboBox<String> roleChoiceBox;
    @FXML
    private Button updateButton;
    @FXML
    private Label topLevel;

    private final String[] passType = {"Ticket Manager", "Ride Manager", "Shop Manager"};


    private Employee employeeToUpdate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleChoiceBox.getItems().addAll(passType);
        updateButton.setOnAction(event -> updateEmployeeInDatabase());
    }

    public void setEmployeeDetails(Employee employee) {
        this.employeeToUpdate = employee;
        topLevel.setText(employee.getName());

        tf_employeeid.setText(employee.getEmployeeID());
        tf_name.setText(employee.getName());
        tf_email.setText(employee.getEmail());
        tf_address.setText(employee.getAddress());
        tf_phone.setText(employee.getPhone());
        roleChoiceBox.setValue(employee.getJobRole());
        tf_salary.setText(String.valueOf(employee.getSalary()));
        tf_username.setText(employee.getUsername());
        tf_password.setText(employee.getPassword());
    }

    private void updateEmployeeInDatabase() {
        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {

            //Checking if NewUserName Exits
            String checkUsernameQuery = serverCred.selectEmployeeByUsernameNotEqualID;

            try (var preparedStatement = connection.prepareStatement(checkUsernameQuery)) {
                preparedStatement.setString(1, tf_username.getText());
                preparedStatement.setString(2, employeeToUpdate.getEmployeeID());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        // If username exists, show a warning and exit
                        warningAlert.showWarningAlert("Username already exists!", "Select another username");
                        return;
                    }
                }
            }

            String updateQuery = serverCred.updateEmployeeByID;

            serverConnect.executeUpdate(connection, updateQuery,
                    tf_name.getText(),
                    tf_address.getText(),
                    tf_phone.getText(),
                    tf_email.getText(),
                    roleChoiceBox.getValue(),
                    Integer.parseInt(tf_salary.getText()),
                    tf_username.getText(),
                    tf_password.getText(),
                    employeeToUpdate.getEmployeeID()
            );

            updateButton.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
            failureAlert.showFailureAlert("Error", "Update Failed", "An error occurred while updating the ride.");
        }
    }

}
