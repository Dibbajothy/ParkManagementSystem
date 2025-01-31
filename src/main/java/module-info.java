module me.logger {
        requires javafx.controls;
        requires javafx.fxml;
        requires mysql.connector.j;
        requires java.sql;
        requires fontawesomefx;
        requires java.desktop;

        // Open packages for JavaFX reflection
        opens me.logger to javafx.fxml;
        opens me.logger.AdminControllers to javafx.fxml;
        opens me.logger.EmployeeControllers to javafx.fxml;

        // Export packages for compile-time access
        exports me.logger;
        exports me.logger.AdminControllers;
        exports me.logger.EmployeeControllers;

        opens me.logger.Utility.CustomAlerts to javafx.base;
        exports me.logger.Utility.CustomAlerts;

        exports me.logger.Utility.GeneralObjects;
        opens me.logger.Utility.GeneralObjects to javafx.base;

        exports me.logger.Utility.RandomGenerators;
        opens me.logger.Utility.RandomGenerators to javafx.base;

        exports me.logger.Utility.StringPaths;
        opens me.logger.Utility.StringPaths to javafx.base;

        exports me.logger.Utility.HandleServer;
        opens me.logger.Utility.HandleServer to javafx.base;

        exports me.logger.Utility.WorkOfView;
        opens me.logger.Utility.WorkOfView to javafx.base;
        opens me.logger.EmployeeControllers.TicketManager to javafx.fxml;
        exports me.logger.EmployeeControllers.TicketManager;
        exports me.logger.EmployeeControllers.RideManager;
        opens me.logger.EmployeeControllers.RideManager to javafx.fxml;


}
