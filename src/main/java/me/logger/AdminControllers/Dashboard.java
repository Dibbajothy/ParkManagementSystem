package me.logger.AdminControllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {

    @FXML private Label clockLabel, totalEmployees, totalRides, todayRevenue, runningRides, stoppedRides, maintainedRides;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startClock();
        loadEmployeeAndRideAndRevenueData();
    }


    private void startClock() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");

        clockLabel.setText(LocalDate.now().format(formatter));

        Timeline clock = new Timeline(new KeyFrame(Duration.hours(24), event -> {
            clockLabel.setText(LocalDate.now().format(formatter));
        }));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }


    private void loadEmployeeAndRideAndRevenueData() {
        String totalEmployeesQuery = serverCred.totalEmployeeQuery;
        String totalRidesQuery = serverCred.totalRidesQuery;
        String totalRunningRidesQuery = serverCred.totalRunningRidesQuery;
        String totalStoppedRidesQuery = serverCred.totalStoppedRidesQuery;
        String totalMaintenanceRidesQuery = serverCred.totalMaintenanceRidesQuery;
        String todayRevenueQuery = serverCred.todayRevenueQuery;

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {

            try (PreparedStatement psTotalEmployees = connection.prepareStatement(totalEmployeesQuery);
                 ResultSet rsTotalEmployees = psTotalEmployees.executeQuery()) {
                if (rsTotalEmployees.next()) {
                    totalEmployees.setText(String.valueOf(rsTotalEmployees.getInt(1)));
                }
            }

            try (PreparedStatement psTotalRides = connection.prepareStatement(totalRidesQuery);
                 ResultSet rsTotalRides = psTotalRides.executeQuery()) {
                if (rsTotalRides.next()) {
                    totalRides.setText(String.valueOf(rsTotalRides.getInt(1)));
                }
            }

            try (PreparedStatement psRunningRides = connection.prepareStatement(totalRunningRidesQuery);
                 ResultSet rsRunningRides = psRunningRides.executeQuery()) {
                if (rsRunningRides.next()) {
                    runningRides.setText(String.valueOf(rsRunningRides.getInt(1)));
                }
            }

            try (PreparedStatement psStoppedRides = connection.prepareStatement(totalStoppedRidesQuery);
                 ResultSet rsStoppedRides = psStoppedRides.executeQuery()) {
                if (rsStoppedRides.next()) {
                    stoppedRides.setText(String.valueOf(rsStoppedRides.getInt(1)));
                }
            }

            try (PreparedStatement psMaintainedRides = connection.prepareStatement(totalMaintenanceRidesQuery);
                 ResultSet rsMaintainedRides = psMaintainedRides.executeQuery()) {
                if (rsMaintainedRides.next()) {
                    maintainedRides.setText(String.valueOf(rsMaintainedRides.getInt(1)));
                }
            }

            try (PreparedStatement psTodayRevenue = connection.prepareStatement(todayRevenueQuery)) {
                psTodayRevenue.setString(1, LocalDate.now().toString());
                try (ResultSet rsTodayRevenue = psTodayRevenue.executeQuery()) {
                    if (rsTodayRevenue.next()) {
                        todayRevenue.setText( "$" + String.valueOf(rsTodayRevenue.getInt(1)));
                    }
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
