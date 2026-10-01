import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentCourseManagement extends JFrame {

    JList<String> courseList;
    JTable table;
    DefaultTableModel model;
    JTextField nameField;
    JButton addButton, removeButton;

    StudentCourseManagement() {

        setTitle("Student Course Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Student Name
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Student Name:"));

        nameField = new JTextField(15);
        topPanel.add(nameField);

        add(topPanel, BorderLayout.NORTH);

        // Available Courses
        String[] courses = {
            "Java Programming",
            "Python Programming",
            "Database Management",
            "Computer Networks",
            "Web Development"
        };

        courseList = new JList<>(courses);
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane courseScrollPane = new JScrollPane(courseList);
        courseScrollPane.setBorder(
            BorderFactory.createTitledBorder("Available Courses")
        );

        add(courseScrollPane, BorderLayout.WEST);

        // Table
        model = new DefaultTableModel(
            new String[]{"Student Name", "Selected Course", "Enrollment Status"},
            0
        );

        table = new JTable(model);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(
            BorderFactory.createTitledBorder("Course Registrations")
        );

        add(tableScrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add Registration");
        removeButton = new JButton("Remove Registration");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add Registration
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String student = nameField.getText();
                String course = courseList.getSelectedValue();

                if (student.isEmpty() || course == null) {
                    JOptionPane.showMessageDialog(
                        StudentCourseManagement.this,
                        "Enter student name and select a course!"
                    );
                } else {
                    model.addRow(
                        new Object[]{student, course, "Enrolled"}
                    );

                    nameField.setText("");
                    courseList.clearSelection();
                }
            }
        });

        // Remove Registration
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if (row != -1) {
                    model.removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(
                        StudentCourseManagement.this,
                        "Select a registration to remove!"
                    );
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentCourseManagement();
    }
}
