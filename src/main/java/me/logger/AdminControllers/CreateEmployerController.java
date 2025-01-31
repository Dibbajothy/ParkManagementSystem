package me.logger.AdminControllers;

import javafx.scene.control.ChoiceBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.GeneralObjects.Employee;
import me.logger.Utility.RandomGenerators.employeeIDgen;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEmployerController implements Initializable {

    @FXML
    private Button createButton;
    @FXML
    private TextField tf_name, tf_email, tf_address, tf_phone, tf_salary, tf_username, tf_password;
    @FXML
    private ChoiceBox<String> roleChoiceBox;

    private final String[] jobRole = {"Ticket Manager", "Ride Manager", "Shop Manager"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        roleChoiceBox.getItems().addAll(jobRole);
        roleChoiceBox.setValue("Select Role");

        createButton.setOnAction(event -> handleCreateButtonAction(event));
    }

    private void handleCreateButtonAction(ActionEvent event) {
        if (areFieldsFilled()) {
            try {
                String employeeID = employeeIDgen.generateUniqueID();
                Employee employee = new Employee(
                        employeeID,
                        tf_name.getText().trim(),
                        tf_address.getText().trim(),
                        tf_phone.getText().trim(),
                        tf_email.getText().trim(),
                        roleChoiceBox.getValue(),
                        Integer.parseInt(tf_salary.getText().trim()),
                        tf_username.getText().trim(),
                        tf_password.getText().trim()
                );
                AdminDBUtils.createEmployment(event, employee);
            } catch (Exception e) {
                failureAlert.showFailureAlert("Error", "Failed to Create Employee", "An error occurred while creating the employee.");
                e.printStackTrace();
            }
        } else {
            failureAlert.showFailureAlert("Not Filled Up", "Invalid Inputs", "Please ensure all fields are filled correctly.");
        }
    }

    private boolean areFieldsFilled() {
        return !tf_name.getText().trim().isEmpty() &&
                !tf_address.getText().trim().isEmpty() &&
                !tf_phone.getText().trim().isEmpty() &&
                !tf_email.getText().trim().isEmpty() &&
                roleChoiceBox.getValue() != null &&
                !roleChoiceBox.getValue().equals("Select Role") &&
                isValidSalary(tf_salary.getText().trim()) &&
                !tf_username.getText().trim().isEmpty() &&
                !tf_password.getText().trim().isEmpty();
    }

    private boolean isValidSalary(String salary) {
        try {
            Integer.parseInt(salary);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
