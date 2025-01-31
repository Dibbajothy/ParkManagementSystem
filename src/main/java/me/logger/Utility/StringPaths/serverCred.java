package me.logger.Utility.StringPaths;

public class serverCred {

    public static final String username = "root";
    public static final String password = "hello@boy";

    public static final String DBurl = "jdbc:mysql://localhost:3306/admin";


    public static final String selectAllFromTicketsByUniqueID = "SELECT * FROM ticket_values WHERE unique_id=?";
    public static final String selectAllFromEmployeesByUsername = "SELECT * FROM users WHERE username=?";
    public static final String selectAllFromEmployees = "SELECT * FROM users";
    public static final String selectAllFromRides = "SELECT * FROM rides";
    public static final String selectAllFromRidesByName = "SELECT * FROM rides WHERE rideName=?";

    public static final String selectRunningRides = "SELECT rideName FROM rides WHERE status = 'Running'";
    public static final String selectRideTicketPriceByRideName = "SELECT ticketPrice FROM rides WHERE rideName = ?";
    public static final String selectEmployeeByUsernameNotEqualID = "SELECT COUNT(*) FROM users WHERE username = ? AND employeeID != ?";


    public static final String employeeLoginPasswordAndJobRoleFromUser = "SELECT password, jobRole FROM users WHERE username=?";
    public static final String adminLoginPasswordFromAdmin = "SELECT password FROM admins WHERE adminname=?";


    public static final String insertAllToTickets = "INSERT INTO ticket_values (name,email,phone,unique_id,pass_type,creation_date,lockers,adult_num,child_num,selected_rides,total_cost) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    public static final String insertAllToEmployee = "INSERT INTO users (employeeID, name, address, phone, email, jobRole, salary, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String insertAllToRides = "INSERT INTO rides (rideName, minHeight, status, capacity, ticketPrice) VALUES (?, ?, ?, ?, ?)";


    public static final String deleteFromEmployeeByEmployeeID = "DELETE FROM users WHERE employeeID=?";
    public static final String deleteFromRidesByRideName = "DELETE FROM rides WHERE rideName=?";



    public static final String updateRideByName = "UPDATE rides SET rideName = ?, minHeight = ?, capacity = ?, status = ?, ticketPrice = ? WHERE rideName = ?";
    public static final String updateEmployeeByID = "UPDATE users SET name=?, address=?, phone=?, email=?, jobRole=?, salary=?, username=?, password=? WHERE employeeID = ?";


    //Revenue
    public static final String totalRevenueQuery = "SELECT SUM(total_cost) FROM ticket_values";
    public static final String totalTicketsQuery = "SELECT COUNT(*) FROM ticket_values";
    public static final String todayRevenueQuery = "SELECT SUM(total_cost) FROM ticket_values WHERE creation_date = ?";
    public static final String todayTicketsQuery = "SELECT COUNT(*) FROM ticket_values WHERE creation_date = ?";

    //ADminDashboard
    public static final String totalEmployeeQuery = "SELECT COUNT(*) FROM users";
    public static final String totalRidesQuery = "SELECT COUNT(*) FROM rides";
    public static final String totalRunningRidesQuery = "SELECT COUNT(*) FROM rides WHERE status = 'Running'";
    public static final String totalStoppedRidesQuery = "SELECT COUNT(*) FROM rides WHERE status = 'Stopped'";
    public static final String totalMaintenanceRidesQuery = "SELECT COUNT(*) FROM rides WHERE status = 'Maintenance'";

    //TIcketManager
    public static final String totalTicketsByDateQuery = "SELECT COUNT(*) FROM ticket_values WHERE creation_date = ?";
    public static final String vipTicketsByDateQuery = "SELECT COUNT(*) FROM ticket_values WHERE creation_date = ? AND pass_type = 'VIP Pass'";
    public static final String mostPopularRideQuery = "SELECT selected_rides FROM ticket_values WHERE creation_date = ?";
    public static final String totalAdultsByDateQuery = "SELECT SUM(adult_num) FROM ticket_values WHERE creation_date = ?";
    public static final String totalChildsByDateQuery = "SELECT SUM(child_num) FROM ticket_values WHERE creation_date = ?";
    public static final String avgCostByDateQuery = "SELECT AVG(total_cost) FROM ticket_values WHERE creation_date = ?";




}
