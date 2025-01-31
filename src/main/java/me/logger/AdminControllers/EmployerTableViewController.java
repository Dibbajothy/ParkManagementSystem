package me.logger.AdminControllers;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.CustomAlerts.successAlert;
import me.logger.Utility.CustomAlerts.warningAlert;
import me.logger.Utility.GeneralObjects.Employee;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.StringPaths.serverCred;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EmployerTableViewController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Button detailsButton;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextField searchField;



    @FXML
    private TableView<Employee> table_employees;
    @FXML
    private TableColumn<Employee, String> table_id;
    @FXML
    private TableColumn<Employee, String> table_employee_name;
    @FXML
    private TableColumn<Employee, String> table_address;
    @FXML
    private TableColumn<Employee, String> table_phone;
    @FXML
    private TableColumn<Employee, String> table_email;
    @FXML
    private TableColumn<Employee, String> table_job_role;
    @FXML
    private TableColumn<Employee, Integer> table_salary;
    @FXML
    private TableColumn<Employee, String> table_username;
    @FXML
    private TableColumn<Employee, String> table_password;

    ObservableList<Employee> listM;

    private FilteredList<Employee> filteredList;


    public void initialize(URL url, ResourceBundle rb) {

        table_id.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        table_employee_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        table_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        table_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        table_job_role.setCellValueFactory(new PropertyValueFactory<>("jobRole"));
        table_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        table_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        table_password.setCellValueFactory(new PropertyValueFactory<>("password"));

        listM = getEmployeeList();
        filteredList = new FilteredList<>(listM, employee->true); //loading all employee from ListM to filteredList

        SortedList<Employee> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table_employees.comparatorProperty());
        table_employees.setItems(sortedList);


        //searchingBYname
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return employee.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });


//        table_employees.setItems(listM);

        deleteButton.setOnAction(event -> deleteSelectedEmployee());
        detailsButton.setOnAction(event -> showDetails());
        addButton.setOnAction(event -> handleAddEmployee());
        updateButton.setOnAction(event -> updateEmployee());

    }

    private static ObservableList<Employee> getEmployeeList() {
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();
        String query = serverCred.selectAllFromEmployees;

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {
            if (connection != null) {
                try (ResultSet resultSet = connection.prepareStatement(query).executeQuery()) {

                    while (resultSet.next()) {
                        employeeList.add(new Employee(
                                resultSet.getString("employeeID"),
                                resultSet.getString("name"),
                                resultSet.getString("address"),
                                resultSet.getString("phone"),
                                resultSet.getString("email"),
                                resultSet.getString("jobRole"),
                                resultSet.getInt("salary"),
                                resultSet.getString("username"),
                                resultSet.getString("password")
                        ));
                    }
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    private void deleteSelectedEmployee() {

        Employee selectedEmployee = table_employees.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            String employeeID = selectedEmployee.getEmployeeID();
            String deleteQuery = serverCred.deleteFromEmployeeByEmployeeID;

            try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {
                serverConnect.executeUpdate(connection, deleteQuery, employeeID);
                listM.remove(selectedEmployee);
                table_employees.refresh();

                successAlert.showSuccessAlert("The employee with ID " + employeeID + " was successfully deleted.");

            } catch (Exception e) {
                e.printStackTrace();

                failureAlert.showFailureAlert("Failed", "Deletation Failed", "An error occurred while trying to delete the employee.");
            }
        } else {
            warningAlert.showWarningAlert("No Employee Selected", "Please select an employee to delete.");
        }
    }

    private void showDetails() {
        Employee selectedEmployee = table_employees.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlDictionary.admin.employeePopUpView));
                Parent root = loader.load();
                // Get the controller for the popup
                EmployeeDetailsPopupController popupController = loader.getController();
                // Set the employee details in the popup
                popupController.setEmployeeDetails(selectedEmployee);
                // Create and show the popup stage
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setTitle("Employee Details");
                popupStage.setScene(new Scene(root));
                popupStage.setResizable(false);
                popupStage.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            warningAlert.showWarningAlert("No employee selected", "Please select an employee from the table to view details.");
        }
    }



    @FXML
    private void handleAddEmployee() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlDictionary.admin.adminPanelCreateEmployment));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Create Employee");
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);
            popupStage.showAndWait();

            reloadEmployeeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reloadEmployeeData() {
        // Reload the data from the database
        listM = getEmployeeList();

        filteredList = new FilteredList<>(listM, employee -> true);

        SortedList<Employee> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(table_employees.comparatorProperty());

        table_employees.setItems(sortedList);
        table_employees.refresh();
    }

    private void updateEmployee() {
        Employee selectedEmployee = table_employees.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlDictionary.admin.updateEmployeePopUp));
                Parent root = loader.load();
                // Get the controller for the popup
                employeeUpdatePopup popupController = loader.getController();
                // Prepopulate the popup fields with selected employee data
                popupController.setEmployeeDetails(selectedEmployee);

                //showThePopUp
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setTitle("Update Employee");
                popupStage.setScene(new Scene(root));
                popupStage.setResizable(false);
                popupStage.showAndWait();

                reloadEmployeeData();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        else {
            warningAlert.showWarningAlert("No Employee Selected", "Please select an Employee to update.");
        }
    }

}
