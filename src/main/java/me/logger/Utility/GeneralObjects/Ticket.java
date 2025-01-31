package me.logger.Utility.GeneralObjects;

import me.logger.Utility.HandleServer.serverConnect;
import me.logger.Utility.StringPaths.serverCred;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class Ticket{

    public String name, email, phone, id, date, pass;
    public int  numberofadult, numberofchild, lockers;
    public List<String> selectedRides;
    private int basePrice = 500;


    public Ticket(String name, String email, String phone, String id, int numberofadult, int numberofchild, int lockers, String date, String pass, List<String> selectedRides) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.numberofadult = numberofadult;
        this.numberofchild = numberofchild;
        this.lockers = lockers;
        this.date = date;
        this.pass = pass;
        this.selectedRides = selectedRides;
    }

    public double priceCal() {
        double totalPrice = basePrice;

        try (Connection connection = serverConnect.getConnection(serverCred.DBurl, serverCred.username, serverCred.password)) {
            if (connection != null) {

                String query = serverCred.selectRideTicketPriceByRideName;

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                    for (String rideName : selectedRides) {
                        preparedStatement.setString(1, rideName);
                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            if (resultSet.next()) {
                                totalPrice += resultSet.getInt("ticketPrice");
                            }
                        }
                    }

                }

            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if ("VIP Pass".equalsIgnoreCase(pass)) {
            totalPrice *= 1.2;
        } else if ("Standard Pass".equalsIgnoreCase(pass)) {
            totalPrice *= 1.1;
        }

        totalPrice += lockers * 50;

        totalPrice += (numberofadult * 100) + (numberofchild * 50);

        return totalPrice;
    }


}
