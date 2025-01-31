package me.logger.Utility.RandomGenerators;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class employeeIDgen {

    public static String generateUniqueID() {

        String prefix = "EMP";

        // Get current timestamp (compact format)
        String timestamp = new SimpleDateFormat("yyMMddHHmmss").format(new Date());

        // Generate a short random UUID
        String randomUUID = UUID.randomUUID().toString().substring(0, 4);

        // Combine prefix, timestamp, and random UUID
        return prefix + timestamp + randomUUID;
    }

}
