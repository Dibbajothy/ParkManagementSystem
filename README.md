# Park Management System

This project is a Java-based **Park Management System** built in **JetBrains IntelliJ** using **JavaFX** as the framework and **MySQL** as the database. It provides functionality for managing employees, rides, and other features in the future.

## Requirements
To run this application, ensure you have the following installed on your system:

1. **Java Development Kit (JDK) 23**
2. **JetBrains IntelliJ IDEA**
3. **JavaFX SDK 23.0.1** (included in IntelliJ under Project Structure)
4. **MySQL Server (version 8.0 or higher)**
5. **JDBC Driver for MySQL** (usually included with MySQL or IntelliJ)

## Installation Steps
Follow these steps to set up and run the application:

### Step 1: Clone the Repository
1. Open a terminal or Git Bash.
2. Run the following command to clone the repository:
   ```bash
   git clone https://github.com/Dibbajothy/ParkManagementSystem.git
   ```
3. Navigate to the project folder:
   ```bash
   cd ParkManagementSystem
   ```

The repository includes the compiled JAR file (**ParkManagementSystem.jar**), so there is no need to compile the source code manually. However, the database must be set up correctly since static database credentials are used in the program:

```java
public static final String username = "root";
public static final String password = "hello@boy";
public static final String DBurl = "jdbc:mysql://localhost:3306/admin";
```

### Step 2: Set Up the Database
1. Open **MySQL Workbench** or any MySQL client.
2. Ensure that the local MySQL connection uses the default **port 3306** and **hostname = "localhost"**. If not, change it accordingly or create a new connection with the following settings:
   - **Hostname:** localhost
   - **Port:** 3306
   - **Username:** root
   - **Password:** hello@boy
3. If only the username and password do not match, update them using SQL queries:
   ```sql
   -- Change MySQL Password
   ALTER USER 'your_username'@'localhost' IDENTIFIED BY 'hello@boy';
   
   -- Rename MySQL Username
   RENAME USER 'old_username'@'localhost' TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```
4. Create a new database schema named **admin**.
5. Import the `database.sql` file to set up the required tables (**admins, rides, ticket_values, users**):
   - In **MySQL Workbench**, go to **Server** → **Data Import**.
   - Select **Import from Self-Contained File** and choose `database.sql`.
   - Under **Default Target Schema**, select **admin**.
   - Click **Start Import** to import the tables.

### Step 3: Run the Project
1. Open the **ParkManagementSystem.jar** file by double-clicking or running it via the command line:
   ```bash
   java -jar ParkManagementSystem.jar
   ```
2. Use the following default credentials to log in:

   **Admin Panel:**
   - Username: `admin`
   - Password: `admin`

   **Ticket Manager Login:**
   - Username: `usr`
   - Password: `usr`
   - (You can also create a Ticket Manager from the Admin panel with any username and password.)

   **Ride Manager Login:**
   - Username: `dd`
   - Password: `dd`
   - (You can also create a Ride Manager from the Admin panel with any username and password.)

### Step 4: Troubleshooting
If you encounter issues:
- Ensure the MySQL database is running and accessible.
- Check that the JDBC driver is properly linked in the project libraries.
- Verify that the database credentials match those in the source code.

### Alternative: Running the Project from Source Code
If the JAR file does not work, you can run the project from IntelliJ:
1. Open the project in **IntelliJ IDEA**.
2. Ensure **Maven (pom.xml)** loads all dependencies automatically, or manually add **JavaFX SDK 23.0.1** under **Project Structure → Libraries**.
3. Run the project from IntelliJ.

****Where ever you run that jar file the *.txt file for a ticket will be created in that same directory inside a GeneratedTicket directory, if that directory doesn't exists then it will create it in its own . 

Now, you are all set to explore and manage the Park Management System! 🚀

