package me.logger.AdminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.GeneralObjects.Employee;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.StringPaths.serverCred;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String adminname) {
        Parent root = null;

        if(adminname != null) {
            try{
                FXMLLoader loader = new FXMLLoader(AdminDBUtils.class.getResource(fxmlFile));
                root = loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root = FXMLLoader.load(AdminDBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 650));
        stage.show();

    }


    public static void logInAdmin(ActionEvent event, String adminname, String password){
        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {

            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                preparedStatement = connection.prepareStatement(serverCred.adminLoginPasswordFromAdmin);
                preparedStatement.setString(1, adminname);
                resultSet = preparedStatement.executeQuery();

                if (!resultSet.next()) {
                    failureAlert.showFailureAlert("NO MATCH", "ERROR", "No Such Admin");
                    return;
                }

                String retrievedPassword = resultSet.getString("password");

                if (retrievedPassword.equals(password)) {
                    changeScene(event, fxmlDictionary.admin.adminPanel, "Welcome", adminname);
                } else {
                    failureAlert.showFailureAlert("NOT MATCH", "ERROR", "Admin Credentials Incorrect");
                }

            } finally {
                serverConnect.closeResources(resultSet, preparedStatement);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createEmployment(ActionEvent event, Employee employee) {
        try(Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {

            if (serverConnect.recordExists(connection, serverCred.selectAllFromEmployeesByUsername, employee.getUsername())){
                failureAlert.showFailureAlert("Username Invalid", "Invalid", "Username already exists");
            }else {
                serverConnect.executeUpdate(connection, serverCred.insertAllToEmployee, employee.getEmployeeID(), employee.getName(), employee.getAddress(), employee.getPhone(), employee.getEmail(), employee.getJobRole(), employee.getSalary(), employee.getUsername(), employee.getPassword());

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.close();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }



}
