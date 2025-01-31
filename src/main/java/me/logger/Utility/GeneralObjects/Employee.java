package me.logger.Utility.GeneralObjects;


public class Employee {


    private String employeeID , name, address, phone, email, jobRole, username, password;
    private int salary;

    public Employee(String employeeID, String name, String address, String phone, String email, String jobRole, int salary, String username, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.jobRole = jobRole;
        this.salary = salary;
        this.username = username;
        this.password = password;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getSalary() {
        return salary;
    }


    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}