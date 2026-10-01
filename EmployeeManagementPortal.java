import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// ===================== MODEL =====================
class EmployeeModel {

    private String username = "admin";
    private String password = "admin123";

    private String employeeId;
    private String employeeName;
    private String department;

    // Login validation
    public boolean validateLogin(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    // Change password
    public boolean changePassword(String oldPass, String newPass) {
        if (password.equals(oldPass)) {
            password = newPass;
            return true;
        }
        return false;
    }

    // Store employee details
    public void addEmployee(String id, String name, String dept) {
        employeeId = id;
        employeeName = name;
        department = dept;
    }

    public String getEmployeeDetails() {
        if (employeeId == null) {
            return "No employee added.";
        }

        return "Employee ID: " + employeeId
                + "\nEmployee Name: " + employeeName
                + "\nDepartment: " + department;
    }
}


// ===================== VIEW =====================
class EmployeeView {

    JFrame loginFrame;
    JFrame mainFrame;

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;

    JTextField idField;
    JTextField nameField;
    JTextField deptField;

    EmployeeView() {
        createLoginWindow();
    }

    // Login Window
    void createLoginWindow() {

        loginFrame = new JFrame("Employee Management - Login");
        loginFrame.setSize(400, 250);
        loginFrame.setLayout(new GridLayout(3, 2, 10, 10));
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginFrame.add(new JLabel("Username:"));
        usernameField = new JTextField();
        loginFrame.add(usernameField);

        loginFrame.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        loginFrame.add(passwordField);

        loginButton = new JButton("Login");
        loginFrame.add(new JLabel(""));
        loginFrame.add(loginButton);

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    // Main Application Window
    void createMainWindow() {

        mainFrame = new JFrame("Employee Management Portal");
        mainFrame.setSize(500, 350);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        // Employee Menu
        JMenu employeeMenu = new JMenu("Employee");

        JMenuItem addEmployee = new JMenuItem("Add Employee");
        JMenuItem viewEmployee = new JMenuItem("View Employee");

        employeeMenu.add(addEmployee);
        employeeMenu.add(viewEmployee);

        // Tools Menu
        JMenu toolsMenu = new JMenu("Tools");

        JMenuItem changePassword = new JMenuItem("Change Password");

        toolsMenu.add(changePassword);

        // Exit Menu
        JMenu exitMenu = new JMenu("Exit");

        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem exitApplication = new JMenuItem("Exit Application");

        exitMenu.add(logout);
        exitMenu.add(exitApplication);

        menuBar.add(employeeMenu);
        menuBar.add(toolsMenu);
        menuBar.add(exitMenu);

        mainFrame.setJMenuBar(menuBar);

        JLabel label = new JLabel(
                "Welcome to Employee Management Portal",
                SwingConstants.CENTER
        );

        mainFrame.add(label);

        // Menu actions are handled by Controller
        addEmployee.addActionListener(e ->
                EmployeeController.showAddEmployeeForm());

        viewEmployee.addActionListener(e ->
                EmployeeController.viewEmployee());

        changePassword.addActionListener(e ->
                EmployeeController.changePassword());

        logout.addActionListener(e ->
                EmployeeController.logout());

        exitApplication.addActionListener(e ->
                System.exit(0));

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    // Add Employee Form
    void showEmployeeForm() {

        JFrame frame = new JFrame("Add Employee");
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel("Employee ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("Employee Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Department:"));
        deptField = new JTextField();
        frame.add(deptField);

        JButton addButton = new JButton("Add Employee");

        frame.add(new JLabel(""));
        frame.add(addButton);

        addButton.addActionListener(e ->
                EmployeeController.saveEmployee(frame));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Change Password Form
    void showChangePasswordForm() {

        JFrame frame = new JFrame("Change Password");
        frame.setSize(400, 250);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel("Old Password:"));
        JPasswordField oldPassword = new JPasswordField();
        frame.add(oldPassword);

        frame.add(new JLabel("New Password:"));
        JPasswordField newPassword = new JPasswordField();
        frame.add(newPassword);

        frame.add(new JLabel("Confirm Password:"));
        JPasswordField confirmPassword = new JPasswordField();
        frame.add(confirmPassword);

        JButton changeButton = new JButton("Change Password");

        frame.add(new JLabel(""));
        frame.add(changeButton);

        changeButton.addActionListener(e ->
                EmployeeController.updatePassword(
                        frame,
                        oldPassword,
                        newPassword,
                        confirmPassword
                ));

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


// ===================== CONTROLLER =====================
class EmployeeController {

    static EmployeeModel model = new EmployeeModel();
    static EmployeeView view;

    EmployeeController(EmployeeView v) {
        view = v;

        // Login button action
        view.loginButton.addActionListener(e -> login());
    }

    // Login
    static void login() {

        String username = view.usernameField.getText();
        String password = new String(
                view.passwordField.getPassword()
        );

        if (model.validateLogin(username, password)) {

            JOptionPane.showMessageDialog(
                    view.loginFrame,
                    "Login Successful!"
            );

            view.loginFrame.dispose();
            view.createMainWindow();

        } else {

            JOptionPane.showMessageDialog(
                    view.loginFrame,
                    "Invalid Username or Password!",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Add Employee
    static void showAddEmployeeForm() {
        view.showEmployeeForm();
    }

    static void saveEmployee(JFrame frame) {

        String id = view.idField.getText();
        String name = view.nameField.getText();
        String dept = view.deptField.getText();

        if (id.isEmpty() || name.isEmpty() || dept.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Please enter all employee details."
            );

        } else {

            model.addEmployee(id, name, dept);

            JOptionPane.showMessageDialog(
                    frame,
                    "Employee Added Successfully!"
            );

            frame.dispose();
        }
    }

    // View Employee
    static void viewEmployee() {

        JOptionPane.showMessageDialog(
                view.mainFrame,
                model.getEmployeeDetails(),
                "Employee Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Change Password
    static void changePassword() {
        view.showChangePasswordForm();
    }

    static void updatePassword(
            JFrame frame,
            JPasswordField oldPassword,
            JPasswordField newPassword,
            JPasswordField confirmPassword) {

        String oldPass = new String(
                oldPassword.getPassword()
        );

        String newPass = new String(
                newPassword.getPassword()
        );

        String confirmPass = new String(
                confirmPassword.getPassword()
        );

        if (!newPass.equals(confirmPass)) {

            JOptionPane.showMessageDialog(
                    frame,
                    "New Password and Confirm Password do not match!"
            );

        } else if (newPass.isEmpty()) {

            JOptionPane.showMessageDialog(
                    frame,
                    "New Password cannot be empty!"
            );

        } else if (model.changePassword(oldPass, newPass)) {

            JOptionPane.showMessageDialog(
                    frame,
                    "Password Changed Successfully!"
            );

            frame.dispose();

        } else {

            JOptionPane.showMessageDialog(
                    frame,
                    "Old Password is incorrect!"
            );
        }
    }

    // Logout
    static void logout() {

        view.mainFrame.dispose();

        view = new EmployeeView();

        new EmployeeController(view);

        JOptionPane.showMessageDialog(
                view.loginFrame,
                "You have been logged out."
        );
    }
}


// ===================== MAIN =====================
public class EmployeeManagementPortal {

    public static void main(String[] args) {

        EmployeeView view = new EmployeeView();

        new EmployeeController(view);
    }
}
