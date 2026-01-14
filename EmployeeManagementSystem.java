package prathamesh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeManagementSystem {
    private JFrame mainFrame;
    private Connection con;

    // GUI Components
    private JTextArea outputArea;
    private JTextField empIdField, empNameField, empDeptField, salIdField, amountField, leaveIdField, leaveDaysField, jobIdField, jobTitleField, jobDeptField, paymentIdField, paymentMethodField, adminIdField, adminNameField, adminRoleField, deptIdField, deptNameField;

    public EmployeeManagementSystem() {
        // Initialize Database Connection
        connectToDatabase();

        // Initialize GUI
        initializeUI();
    }

    private void connectToDatabase() {
        try {
            // Change the database URL, username, and password accordingly
            String url = "jdbc:mysql://localhost:3306/payroll_db";
            String user = "root"; // replace with your username
            String password = "rahul123"; // replace with your password
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            showMessage("Database Connection Error: " + e.getMessage());
        }
    }

    private void initializeUI() {
        mainFrame = new JFrame("Employee Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainFrame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2));

        // Create buttons for different tables
        String[] entities = {"Admin", "Department", "Employee", "Salary", "Job", "Leave", "Payment Method"};
        for (String entity : entities) {
            JButton button = new JButton(entity);
            button.addActionListener(e -> openEntityWindow(entity));
            buttonPanel.add(button);
        }

        mainFrame.add(buttonPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private void openEntityWindow(String entity) {
        JFrame entityFrame = new JFrame(entity + " Operations");
        entityFrame.setSize(400, 300);
        entityFrame.setLayout(new GridLayout(0, 2));

        // Input Fields
        switch (entity) {
            case "Admin":
                adminIdField = new JTextField();
                adminNameField = new JTextField();
                adminRoleField = new JTextField();
                entityFrame.add(new JLabel("Admin ID:"));
                entityFrame.add(adminIdField);
                entityFrame.add(new JLabel("Name:"));
                entityFrame.add(adminNameField);
                entityFrame.add(new JLabel("Role:"));
                entityFrame.add(adminRoleField);
                break;
            case "Department":
                deptIdField = new JTextField();
                deptNameField = new JTextField();
                entityFrame.add(new JLabel("Department ID:"));
                entityFrame.add(deptIdField);
                entityFrame.add(new JLabel("Department Name:"));
                entityFrame.add(deptNameField);
                break;
            case "Employee":
                empIdField = new JTextField();
                empNameField = new JTextField();
                empDeptField = new JTextField();
                entityFrame.add(new JLabel("Employee ID:"));
                entityFrame.add(empIdField);
                entityFrame.add(new JLabel("Name:"));
                entityFrame.add(empNameField);
                entityFrame.add(new JLabel("Department:"));
                entityFrame.add(empDeptField);
                break;
            case "Salary":
                salIdField = new JTextField();
                empIdField = new JTextField();
                amountField = new JTextField();
                entityFrame.add(new JLabel("Salary ID:"));
                entityFrame.add(salIdField);
                entityFrame.add(new JLabel("Employee ID:"));
                entityFrame.add(empIdField);
                entityFrame.add(new JLabel("Amount:"));
                entityFrame.add(amountField);
                break;
            case "Job":
                jobIdField = new JTextField();
                jobTitleField = new JTextField();
                jobDeptField = new JTextField();
                entityFrame.add(new JLabel("Job ID:"));
                entityFrame.add(jobIdField);
                entityFrame.add(new JLabel("Title:"));
                entityFrame.add(jobTitleField);
                entityFrame.add(new JLabel("Department:"));
                entityFrame.add(jobDeptField);
                break;
            case "Leave":
                leaveIdField = new JTextField();
                empIdField = new JTextField();
                leaveDaysField = new JTextField();
                entityFrame.add(new JLabel("Leave ID:"));
                entityFrame.add(leaveIdField);
                entityFrame.add(new JLabel("Employee ID:"));
                entityFrame.add(empIdField);
                entityFrame.add(new JLabel("Leave Days:"));
                entityFrame.add(leaveDaysField);
                break;
            case "Payment Method":
                paymentIdField = new JTextField();
                paymentMethodField = new JTextField();
                entityFrame.add(new JLabel("Payment ID:"));
                entityFrame.add(paymentIdField);
                entityFrame.add(new JLabel("Method:"));
                entityFrame.add(paymentMethodField);
                break;
        }

        // Operation Buttons
        String[] operations = {"Add", "Edit", "Delete", "Display"};
        for (String operation : operations) {
            JButton button = new JButton(operation);
            button.addActionListener(e -> performOperation(entity, operation));
            entityFrame.add(button);
        }

        entityFrame.setVisible(true);
    }

    private void performOperation(String entity, String operation) {
        switch (entity) {
            case "Admin":
                if (operation.equals("Add")) {
                    addAdmin();
                } else if (operation.equals("Edit")) {
                    editAdmin();
                } else if (operation.equals("Delete")) {
                    deleteAdmin();
                } else {
                    displayAdmins();
                }
                break;
            case "Department":
                if (operation.equals("Add")) {
                    addDepartment();
                } else if (operation.equals("Edit")) {
                    editDepartment();
                } else if (operation.equals("Delete")) {
                    deleteDepartment();
                } else {
                    displayDepartments();
                }
                break;
            case "Employee":
                if (operation.equals("Add")) {
                    addEmployee();
                } else if (operation.equals("Edit")) {
                    editEmployee();
                } else if (operation.equals("Delete")) {
                    deleteEmployee();
                } else {
                    displayEmployees();
                }
                break;
            case "Salary":
                if (operation.equals("Add")) {
                    addSalary();
                } else if (operation.equals("Edit")) {
                    editSalary();
                } else if (operation.equals("Delete")) {
                    deleteSalary();
                } else {
                    displaySalaries();
                }
                break;
            case "Job":
                if (operation.equals("Add")) {
                    addJob();
                } else if (operation.equals("Edit")) {
                    editJob();
                } else if (operation.equals("Delete")) {
                    deleteJob();
                } else {
                    displayJobs();
                }
                break;
            case "Leave":
                if (operation.equals("Add")) {
                    addLeave();
                } else if (operation.equals("Edit")) {
                    editLeave();
                } else if (operation.equals("Delete")) {
                    deleteLeave();
                } else {
                    displayLeaves();
                }
                break;
            case "Payment Method":
                if (operation.equals("Add")) {
                    addPaymentMethod();
                } else if (operation.equals("Edit")) {
                    editPaymentMethod();
                } else if (operation.equals("Delete")) {
                    deletePaymentMethod();
                } else {
                    displayPaymentMethods();
                }
                break;
        }
    }

    // Admin CRUD Operations
    private void addAdmin() {
        try {
            String query = "INSERT INTO admins (admin_id, name, role) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(adminIdField.getText()));
            pst.setString(2, adminNameField.getText());
            pst.setString(3, adminRoleField.getText());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Admin Added Successfully!");
            } else {
                showMessage("Failed to Add Admin.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Admin: " + e.getMessage());
        }
    }

    private void editAdmin() {
        // Implementation for editing admin
    }

    private void deleteAdmin() {
        try {
            String query = "DELETE FROM admins WHERE admin_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(adminIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Admin Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Admin. Check if Admin ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Admin: " + e.getMessage());
        }
    }

    private void displayAdmins() {
        try {
            String query = "SELECT * FROM admins";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Admins:\n");
            while (rs.next()) {
                outputArea.append("ID: " + rs.getInt("admin_id") + ", Name: " + rs.getString("name") + ", Role: " + rs.getString("role") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Admins: " + e.getMessage());
        }
    }

    // Department CRUD Operations
    private void addDepartment() {
        try {
            String query = "INSERT INTO departments (dept_id, name) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(deptIdField.getText()));
            pst.setString(2, deptNameField.getText());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Department Added Successfully!");
            } else {
                showMessage("Failed to Add Department.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Department: " + e.getMessage());
        }
    }

    private void editDepartment() {
        // Implementation for editing department
    }

    private void deleteDepartment() {
        try {
            String query = "DELETE FROM departments WHERE dept_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(deptIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Department Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Department. Check if Department ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Department: " + e.getMessage());
        }
    }

    private void displayDepartments() {
        try {
            String query = "SELECT * FROM departments";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Departments:\n");
            while (rs.next()) {
                outputArea.append("Department ID: " + rs.getInt("dept_id") + ", Name: " + rs.getString("name") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Departments: " + e.getMessage());
        }
    }

    // Employee CRUD Operations
    private void addEmployee() {
        try {
            String query = "INSERT INTO employees (emp_id, name, department) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(empIdField.getText()));
            pst.setString(2, empNameField.getText());
            pst.setString(3, empDeptField.getText());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Employee Added Successfully!");
            } else {
                showMessage("Failed to Add Employee.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Employee: " + e.getMessage());
        }
    }

    private void editEmployee() {
        // Implementation for editing employee
    }

    private void deleteEmployee() {
        try {
            String query = "DELETE FROM employees WHERE emp_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(empIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Employee Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Employee. Check if Employee ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Employee: " + e.getMessage());
        }
    }

    private void displayEmployees() {
        try {
            String query = "SELECT * FROM employees";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Employees:\n");
            while (rs.next()) {
                outputArea.append("Employee ID: " + rs.getInt("emp_id") + ", Name: " + rs.getString("name") + ", Department: " + rs.getString("department") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Employees: " + e.getMessage());
        }
    }

    // Salary CRUD Operations
    private void addSalary() {
        try {
            String query = "INSERT INTO salaries (sal_id, emp_id, amount) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(salIdField.getText()));
            pst.setInt(2, Integer.parseInt(empIdField.getText()));
            pst.setDouble(3, Double.parseDouble(amountField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Salary Added Successfully!");
            } else {
                showMessage("Failed to Add Salary.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Salary: " + e.getMessage());
        }
    }

    private void editSalary() {
        // Implementation for editing salary
    }

    private void deleteSalary() {
        try {
            String query = "DELETE FROM salaries WHERE sal_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(salIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Salary Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Salary. Check if Salary ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Salary: " + e.getMessage());
        }
    }

    private void displaySalaries() {
        try {
            String query = "SELECT * FROM salaries";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Salaries:\n");
            while (rs.next()) {
                outputArea.append("Salary ID: " + rs.getInt("sal_id") + ", Employee ID: " + rs.getInt("emp_id") + ", Amount: " + rs.getDouble("amount") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Salaries: " + e.getMessage());
        }
    }

    // Job CRUD Operations
    private void addJob() {
        try {
            String query = "INSERT INTO jobs (job_id, title, department) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(jobIdField.getText()));
            pst.setString(2, jobTitleField.getText());
            pst.setString(3, jobDeptField.getText());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Job Added Successfully!");
            } else {
                showMessage("Failed to Add Job.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Job: " + e.getMessage());
        }
    }

    private void editJob() {
        // Implementation for editing job
    }

    private void deleteJob() {
        try {
            String query = "DELETE FROM jobs WHERE job_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(jobIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Job Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Job. Check if Job ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Job: " + e.getMessage());
        }
    }

    private void displayJobs() {
        try {
            String query = "SELECT * FROM jobs";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Jobs:\n");
            while (rs.next()) {
                outputArea.append("Job ID: " + rs.getInt("job_id") + ", Title: " + rs.getString("title") + ", Department: " + rs.getString("department") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Jobs: " + e.getMessage());
        }
    }

    // Leave CRUD Operations
    private void addLeave() {
        try {
            String query = "INSERT INTO leaves (leave_id, emp_id, days) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(leaveIdField.getText()));
            pst.setInt(2, Integer.parseInt(empIdField.getText()));
            pst.setInt(3, Integer.parseInt(leaveDaysField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Leave Added Successfully!");
            } else {
                showMessage("Failed to Add Leave.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Leave: " + e.getMessage());
        }
    }

    private void editLeave() {
        // Implementation for editing leave
    }

    private void deleteLeave() {
        try {
            String query = "DELETE FROM leaves WHERE leave_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(leaveIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Leave Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Leave. Check if Leave ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Leave: " + e.getMessage());
        }
    }

    private void displayLeaves() {
        try {
            String query = "SELECT * FROM leaves";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Leaves:\n");
            while (rs.next()) {
                outputArea.append("Leave ID: " + rs.getInt("leave_id") + ", Employee ID: " + rs.getInt("emp_id") + ", Days: " + rs.getInt("days") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Leaves: " + e.getMessage());
        }
    }

    // Payment Method CRUD Operations
    private void addPaymentMethod() {
        try {
            String query = "INSERT INTO payment_methods (payment_id, method) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(paymentIdField.getText()));
            pst.setString(2, paymentMethodField.getText());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Payment Method Added Successfully!");
            } else {
                showMessage("Failed to Add Payment Method.");
            }
        } catch (SQLException e) {
            showMessage("Error Adding Payment Method: " + e.getMessage());
        }
    }

    private void editPaymentMethod() {
        // Implementation for editing payment method
    }

    private void deletePaymentMethod() {
        try {
            String query = "DELETE FROM payment_methods WHERE payment_id = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(paymentIdField.getText()));
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Payment Method Deleted Successfully!");
            } else {
                showMessage("Failed to Delete Payment Method. Check if Payment ID exists.");
            }
        } catch (SQLException e) {
            showMessage("Error Deleting Payment Method: " + e.getMessage());
        }
    }

    private void displayPaymentMethods() {
        try {
            String query = "SELECT * FROM payment_methods";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            outputArea.setText("Payment Methods:\n");
            while (rs.next()) {
                outputArea.append("Payment ID: " + rs.getInt("payment_id") + ", Method: " + rs.getString("method") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error Displaying Payment Methods: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeManagementSystem::new);
    }
}

