package me.logger.EmployeeControllers.TicketManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

public class Dashboard implements Initializable {

    @FXML private Label ticketSold, vipTicketSold, mostPopularRide, numberOfAdults, numberOfChilds, averageCosts;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAllFields();
    }

    private void loadAllFields() {

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {

            try (PreparedStatement psTotalTickets = connection.prepareStatement(serverCred.totalTicketsByDateQuery)) {
                psTotalTickets.setString(1, LocalDate.now().toString());
                try (ResultSet rsTotalTickets = psTotalTickets.executeQuery()) {
                    if (rsTotalTickets.next()) {
                        ticketSold.setText(String.valueOf(rsTotalTickets.getInt(1)));
                    }
                }
            }

            try (PreparedStatement psVipTickets = connection.prepareStatement(serverCred.vipTicketsByDateQuery)) {
                psVipTickets.setString(1, LocalDate.now().toString());
                try (ResultSet rsVipTickets = psVipTickets.executeQuery()) {
                    if (rsVipTickets.next()) {
                        vipTicketSold.setText(String.valueOf(rsVipTickets.getInt(1)));
                    }
                }
            }

            try (PreparedStatement psPopularRide = connection.prepareStatement(serverCred.mostPopularRideQuery)) {
                psPopularRide.setString(1, LocalDate.now().toString());
                try (ResultSet rsPopularRide = psPopularRide.executeQuery()) {
                    mostPopularRide.setText(getMostPopularRide(rsPopularRide));
                }
            }

            try (PreparedStatement psTotalAdults = connection.prepareStatement(serverCred.totalAdultsByDateQuery)) {
                psTotalAdults.setString(1, LocalDate.now().toString());
                try (ResultSet rsTotalAdults = psTotalAdults.executeQuery()) {
                    if (rsTotalAdults.next()) {
                        numberOfAdults.setText(String.valueOf(rsTotalAdults.getInt(1)));
                    }
                }
            }

            try (PreparedStatement psTotalChilds = connection.prepareStatement(serverCred.totalChildsByDateQuery)) {
                psTotalChilds.setString(1, LocalDate.now().toString());
                try (ResultSet rsTotalChilds = psTotalChilds.executeQuery()) {
                    if (rsTotalChilds.next()) {
                        numberOfChilds.setText(String.valueOf(rsTotalChilds.getInt(1)));
                    }
                }
            }

            try (PreparedStatement psAvgCost = connection.prepareStatement(serverCred.avgCostByDateQuery)) {
                psAvgCost.setString(1, LocalDate.now().toString());
                try (ResultSet rsAvgCost = psAvgCost.executeQuery()) {
                    if (rsAvgCost.next()) {
                        averageCosts.setText(String.format("$%.2f", rsAvgCost.getDouble(1)));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMostPopularRide(ResultSet rsPopularRide) throws Exception {
        Map<String, Integer> rideCount = new HashMap<>();

        while (rsPopularRide.next()) {
            String rides = rsPopularRide.getString(1);
            if (rides != null) {
                String[] rideArray = rides.split(", ");
                for (String ride : rideArray) {
                    rideCount.put(ride, rideCount.getOrDefault(ride, 0) + 1);
                }
            }
        }

        int maxCount = 0;
        for (int count : rideCount.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }

        List<String> mostPopularRides = new ArrayList<>();
        for (String ride : rideCount.keySet()) {
            if (rideCount.get(ride) == maxCount) {
                mostPopularRides.add(ride);
            }
        }

        return mostPopularRides.isEmpty() ? "No Data" : String.join("\n", mostPopularRides);
    }

}
