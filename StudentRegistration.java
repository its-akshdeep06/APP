import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistration extends JFrame {

    JTextField nameField, regField;
    JRadioButton male, female;
    JComboBox<String> department;
    JButton submit;

    StudentRegistration() {

        setTitle("Student Registration");
        setSize(400, 350);
        setLayout(new GridLayout(5, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Student Name
        add(new JLabel("Student Name:"));
        nameField = new JTextField();
        add(nameField);

        // Register Number
        add(new JLabel("Register Number:"));
        regField = new JTextField();
        add(regField);

        // Gender
        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");

        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        genderPanel.add(male);
        genderPanel.add(female);
        add(genderPanel);

        // Department
        add(new JLabel("Department:"));
        String[] departments = {
            "Computer Science",
            "Information Technology",
            "Electronics",
            "Mechanical",
            "Civil"
        };

        department = new JComboBox<>(departments);
        add(department);

        // Submit Button
        add(new JLabel(""));
        submit = new JButton("Submit");
        add(submit);

        // Button Action
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = nameField.getText();
                String regNo = regField.getText();

                String gender = "";
                if (male.isSelected())
                    gender = "Male";
                else if (female.isSelected())
                    gender = "Female";

                String dept = (String) department.getSelectedItem();

                JOptionPane.showMessageDialog(
                    StudentRegistration.this,
                    "Student Name: " + name +
                    "\nRegister Number: " + regNo +
                    "\nGender: " + gender +
                    "\nDepartment: " + dept,
                    "Registration Details",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentRegistration();
    }
}
