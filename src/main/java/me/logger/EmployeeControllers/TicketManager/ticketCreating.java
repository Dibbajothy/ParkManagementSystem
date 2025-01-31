package me.logger.EmployeeControllers.TicketManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.logger.Utility.CustomAlerts.failureAlert;
import me.logger.Utility.CustomAlerts.successAlert;
import me.logger.Utility.GeneralObjects.Ticket;
import me.logger.Utility.HandleServer.RideService;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.RandomGenerators.TicketTextFile;
import me.logger.Utility.RandomGenerators.ticketIDgen;
import me.logger.Utility.StringPaths.serverCred;
import me.logger.Utility.WorkOfView.*;
import me.logger.Utility.StringPaths.*;


import java.net.URL;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class ticketCreating implements Initializable {

    @FXML
    private TextField tf_numberofchild, tf_name, tf_email, tf_phone, tf_lockers, tf_numberofadult;
    @FXML
    private DatePicker date_picker;
    @FXML
    private ChoiceBox<String> select_pass;
    @FXML
    private Button generateTicket;
    @FXML
    private ListView<String> rideListView;


    private final String[] passType = {"Standard Pass", "VIP Pass"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        select_pass.getItems().addAll(passType);
        //Getting running rides
        ObservableList<String> runningRides = RideService.getRunningRides();

        select_pass.setValue("Pass Type");

        rideListView.setItems(runningRides);
        rideListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        generateTicket.setOnAction(event -> generate(event));

    }

    private List<String> getSelectedRides() {
        return rideListView.getSelectionModel().getSelectedItems();
    }


    private boolean validateInputs() {
        List<TextField> requiredFields = List.of(tf_name, tf_email, tf_phone, tf_numberofadult, tf_numberofchild, tf_lockers);
        boolean areFieldsValid = requiredFields.stream().noneMatch(field -> field.getText().isEmpty());


        if (!areFieldsValid || date_picker.getValue() == null || "Pass Type".equals(select_pass.getValue()) || getSelectedRides().isEmpty()) {
            failureAlert.showFailureAlert(
                    "Validation Error",
                    "Missing or Invalid Inputs",
                    "Please fill in all required fields and at least one ride"
            );
            return false;
        }
        return true;
    }

    @FXML
    public void generate(ActionEvent event) {
        if (!validateInputs()) return;

        String uniqueID = ticketIDgen.generateUniqueID();
        Ticket ticket = createTicketFromInputs(uniqueID);

        createTicketAsync(ticket, event);

    }

    private Ticket createTicketFromInputs(String uniqueID) {
        return new Ticket(
                tf_name.getText(),
                tf_email.getText(),
                tf_phone.getText(),
                uniqueID,
                Integer.parseInt(tf_numberofadult.getText()),
                Integer.parseInt(tf_numberofchild.getText()),
                Integer.parseInt(tf_lockers.getText()),
                date_picker.getValue() != null ? date_picker.getValue().toString() : null,
                select_pass.getValue(),
                getSelectedRides()
        );
    }


    private void createTicketAsync(Ticket ticket, ActionEvent event) {
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                createTicket(ticket);
                return null;
            }
        };

        task.setOnSucceeded(e -> {
            successAlert.showSuccessAlert("Ticket created successfully!");
            sceneControl.changeScene(event, fxmlDictionary.employee.TicketManagerPanel, "Ticket Manager");
        });

        task.setOnFailed(e -> failureAlert.showFailureAlert("Ticket Create Failed", "Error", task.getException().getMessage()));

        new Thread(task).start();
    }


    public static void createTicket(Ticket ticket) {
        try (
                Connection connection = DriverManager.getConnection(serverCred.DBurl, serverCred.username, serverCred.password);
                PreparedStatement psCheckID = connection.prepareStatement(serverCred.selectAllFromTicketsByUniqueID);
                PreparedStatement psInsert = connection.prepareStatement(serverCred.insertAllToTickets)
        ) {
            if (doesTicketExist(psCheckID, ticket.id)) {
                failureAlert.showFailureAlert("Not Allowed", "ID Exists", "Use Unique ID");
                return;
            }
            insertTicket(psInsert, ticket);
            TicketTextFile.generateTicketFile(ticket);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean doesTicketExist(PreparedStatement psCheckID, String ticketId) throws SQLException {
        psCheckID.setString(1, ticketId);
        try (ResultSet resultSet = psCheckID.executeQuery()) {
            return resultSet.isBeforeFirst();
        }
    }

    private static void insertTicket(PreparedStatement psInsert, Ticket ticket) throws SQLException {
        psInsert.setString(1, ticket.name);
        psInsert.setString(2, ticket.email);
        psInsert.setString(3, ticket.phone);
        psInsert.setString(4, ticket.id);
        psInsert.setString(5, ticket.pass);
        psInsert.setDate(6, Date.valueOf(ticket.date));
        psInsert.setInt(7, ticket.lockers);
        psInsert.setInt(8, ticket.numberofadult);
        psInsert.setInt(9, ticket.numberofchild);

        String ridesConcatanated = "";

        for (int i = 0; i < ticket.selectedRides.size(); i++) {
            ridesConcatanated +=  (ticket.selectedRides.get(i));
            if(i != ticket.selectedRides.size() - 1) ridesConcatanated +=  ", ";
        }

        psInsert.setString(10, ridesConcatanated);

        psInsert.setInt(11, (int)ticket.priceCal());

        psInsert.executeUpdate();
    }

}


