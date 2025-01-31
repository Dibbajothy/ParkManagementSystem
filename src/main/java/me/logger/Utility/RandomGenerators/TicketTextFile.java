package me.logger.Utility.RandomGenerators;

import me.logger.Utility.GeneralObjects.Ticket;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TicketTextFile {
    public static void generateTicketFile(Ticket ticket) {
        String fileName = ticket.id + ".txt";
        String directory = System.getProperty("user.dir") + File.separator + "GeneratedTickets";
        File folder = new File(directory);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        File ticketFile = Paths.get(directory, fileName).toFile();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ticketFile))) {
            writer.write("**************************************************\n");
            writer.write("            🎟️ AMUSEMENT PARK TICKET 🎟️           \n");
            writer.write("**************************************************\n\n");

            writer.write("===============================================\n");
            writer.write(String.format("| %-15s: %-25s |\n", "Ticket ID", ticket.id));
            writer.write(String.format("| %-15s: %-25s |\n", "Name", ticket.name));
            writer.write(String.format("| %-15s: %-25s |\n", "Email", ticket.email));
            writer.write(String.format("| %-15s: %-25s |\n", "Phone", ticket.phone));
            writer.write(String.format("| %-15s: %-25s |\n", "Pass Type", ticket.pass));
            writer.write(String.format("| %-15s: %-25s |\n", "Date", ticket.date));
            writer.write(String.format("| %-15s: %-25d |\n", "Lockers", ticket.lockers));
            writer.write(String.format("| %-15s: %-25d |\n", "Adults", ticket.numberofadult));
            writer.write(String.format("| %-15s: %-25d |\n", "Children", ticket.numberofchild));
            writer.write("===============================================\n\n");

            writer.write("🎢 Selected Rides:\n");
            for (String ride : ticket.selectedRides) {
                writer.write("   ➤ " + ride + "\n");
            }

            writer.write("\n===============================================\n");
            writer.write(String.format("| %-15s: $%-25s |\n", "Total Price", ticket.priceCal()));
            writer.write("===============================================\n\n");

            writer.write("✨ Thank you for visiting! Have a great ride! ✨\n");

            System.out.println("Ticket file generated: " + ticketFile.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
