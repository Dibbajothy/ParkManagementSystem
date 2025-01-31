package me.logger.EmployeeControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.fxmlDictionary;
import me.logger.Utility.StringPaths.serverCred;

import java.io.IOException;
import java.sql.*;

public class EmployeeDBUtils {

    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if(username != null ) {
            try{
                FXMLLoader loader = new FXMLLoader(EmployeeDBUtils.class.getResource(fxmlFile));
                root = loader.load();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }else {
            try{
                root = FXMLLoader.load(EmployeeDBUtils.class.getResource(fxmlFile));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 1000, 650));
        stage.show();

    }


    public static void logInEmployee(ActionEvent event, String username, String password) {

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password);
             PreparedStatement preparedStatement = connection.prepareStatement(serverCred.employeeLoginPasswordAndJobRoleFromUser)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (!resultSet.next()) {
                    failureAlert.showFailureAlert("NO MATCH", "ERROR", "No Such Employee");
                    return;
                }

                String retrievedPassword = resultSet.getString("password");
                String jobRole = resultSet.getString("jobRole");

                if (!retrievedPassword.equals(password)) {
                    failureAlert.showFailureAlert("NOT MATCH", "ERROR", "User Credentials Incorrect");
                    return;
                }

                redirectToRole(event, jobRole, username);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void redirectToRole(ActionEvent event, String jobRole, String username) {
        switch (jobRole.toLowerCase()) {
            case "ticket manager":
                changeScene(event, fxmlDictionary.employee.TicketManagerPanel, "Shop Manager", username);
                break;
            case "ride manager":
                changeScene(event, fxmlDictionary.employee.RideManagerPanel, "Cashier Panel", username);
                break;
            case "shop manager":
                changeScene(event, fxmlDictionary.employee.ShopManagerPanel, "Admin Panel", username);
                break;
            default:
                failureAlert.showFailureAlert("UNKNOWN ROLE", "ERROR", "No Access for this Job Role");
                break;
        }
    }


}
