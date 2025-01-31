package me.logger.EmployeeControllers.TicketManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.GeneralObjects.Ticket;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ticketChecking implements Initializable {

    @FXML
    private ListView<String> rideListView;

    @FXML
    private TextField tf_id;
    @FXML
    private Button validateButton;

    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_date;
    @FXML
    private Text passType;
    @FXML
    private TextField tf_passType;
    @FXML
    private TextField tf_numOfAdult;
    @FXML
    private TextField tf_numOfChild;
    @FXML
    private TextField tf_numOfLockers;
    @FXML
    private Text totalAmount;
    @FXML
    private TextField tf_totalAmount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validateButton.setOnAction(event -> validateTicket());
    }

    private void validateTicket() {
        if (!isTicketIDProvided()) {
            failureAlert.showFailureAlert("Validation", "No Ticket ID", "No Ticket ID Found!");
            return;
        }

        try (Connection connection = establishDatabaseConnection()) {
            if (connection != null) {
                Ticket ticket = fetchTicketDetails(connection);
                if (ticket != null) {
                    populateUI(ticket);
                } else {
                    failureAlert.showFailureAlert("Validation", "Invalid Ticket ID", "No ticket found with the given ID.");
                }
            } else {
                System.err.println("Failed to establish tickets database connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isTicketIDProvided() {
        return !tf_id.getText().trim().isEmpty();
    }

    private Connection establishDatabaseConnection() {
        try {
            return serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Ticket fetchTicketDetails(Connection connection) {
        String query = serverCred.selectAllFromTicketsByUniqueID;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tf_id.getText().trim());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Ticket(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("unique_id"),
                            resultSet.getInt("adult_num"),
                            resultSet.getInt("child_num"),
                            resultSet.getInt("lockers"),
                            resultSet.getString("creation_date"),
                            resultSet.getString("pass_type"),
                            plainTextToList(resultSet.getString("selected_rides"))
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void populateUI(Ticket ticket) {
        tf_name.setText(ticket.name);
        tf_email.setText(ticket.email);
        tf_phone.setText(ticket.phone);
        tf_date.setText(ticket.date);
        tf_passType.setText(ticket.pass);
        tf_numOfAdult.setText(String.valueOf(ticket.numberofadult));
        tf_numOfChild.setText(String.valueOf(ticket.numberofchild));
        tf_numOfLockers.setText(String.valueOf(ticket.lockers));
        tf_totalAmount.setText(String.valueOf(TotalAmount(ticket)));

        // Populate rides list
        rideListView.getItems().clear();
        rideListView.getItems().addAll(ticket.selectedRides);
    }

    private int TotalAmount(Ticket ticket) {
        return (int)ticket.priceCal();
    }

    private List<String> plainTextToList(String plainText) {
        List<String> list = new ArrayList<>();

        if (plainText != null && !plainText.isEmpty()) {
            list = Arrays.asList(plainText.split("\\s*,\\s*"));
        }

        return list;
    }
}
