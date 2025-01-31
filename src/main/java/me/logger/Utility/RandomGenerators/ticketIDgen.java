package me.logger.Utility.RandomGenerators;

import java.util.UUID;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ticketIDgen {

    public static String generateUniqueID() {

        String prefix = "TICK";

        // Get current timestamp (compact format)
        String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

        // Generate a short random UUID
        String randomUUID = UUID.randomUUID().toString().substring(0, 4);

        // Combine prefix, timestamp, and random UUID
        return prefix + timestamp + randomUUID;
    }

}
