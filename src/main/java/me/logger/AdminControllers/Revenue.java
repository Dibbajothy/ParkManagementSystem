package me.logger.AdminControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

public class Revenue implements Initializable {

    @FXML private Label dateLabel;
    @FXML private Label totalMoney;
    @FXML private Label totalTicketSold;
    @FXML private Label todayMoney;
    @FXML private Label todayTicketSold;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateLabel.setText(LocalDate.now().toString());
        loadRevenueData();
    }

    private void loadRevenueData() {

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {

            try (PreparedStatement psTotalRevenue = connection.prepareStatement(serverCred.totalRevenueQuery);
                 ResultSet rsTotalRevenue = psTotalRevenue.executeQuery()) {
                if (rsTotalRevenue.next()) {
                    totalMoney.setText(String.valueOf(rsTotalRevenue.getInt(1)));
                }
            }

            try (PreparedStatement psTotalTickets = connection.prepareStatement(serverCred.totalTicketsQuery);
                 ResultSet rsTotalTickets = psTotalTickets.executeQuery()) {
                if (rsTotalTickets.next()) {
                    totalTicketSold.setText(String.valueOf(rsTotalTickets.getInt(1)));
                }
            }

            try (PreparedStatement psTodayRevenue = connection.prepareStatement(serverCred.todayRevenueQuery)) {
                psTodayRevenue.setString(1, LocalDate.now().toString());
                try (ResultSet rsTodayRevenue = psTodayRevenue.executeQuery()) {
                    if (rsTodayRevenue.next()) {
                        todayMoney.setText(String.valueOf(rsTodayRevenue.getInt(1)));
                    }
                }
            }

            try (PreparedStatement psTodayTickets = connection.prepareStatement(serverCred.todayTicketsQuery)) {
                psTodayTickets.setString(1, LocalDate.now().toString());
                try (ResultSet rsTodayTickets = psTodayTickets.executeQuery()) {
                    if (rsTodayTickets.next()) {
                        todayTicketSold.setText(String.valueOf(rsTodayTickets.getInt(1)));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
