package me.logger.AdminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.logger.Utility.GeneralObjects.Employee;

public class EmployeeDetailsPopupController {

    @FXML
    private Label topLevel;
    @FXML
    private TextField tf_employeeid;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_jobRole;
    @FXML
    private TextField tf_salary;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;


    public void setEmployeeDetails(Employee employee) {
        topLevel.setText(employee.getName());
        tf_employeeid.setText(employee.getEmployeeID());
        tf_name.setText(employee.getName());
        tf_email.setText(employee.getEmail());
        tf_address.setText(employee.getAddress());
        tf_phone.setText(employee.getPhone());
        tf_jobRole.setText(employee.getJobRole());
        tf_salary.setText(String.valueOf(employee.getSalary()));
        tf_username.setText(employee.getUsername());
        tf_password.setText(employee.getUsername());
    }

}
