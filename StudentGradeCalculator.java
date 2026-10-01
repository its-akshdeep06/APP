import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// MODEL
class StudentModel {
    private String name;
    private int mark1, mark2, mark3;
    private int total;
    private double average;
    private String grade;

    public void calculate(String name, int m1, int m2, int m3) {
        this.name = name;
        this.mark1 = m1;
        this.mark2 = m2;
        this.mark3 = m3;

        total = mark1 + mark2 + mark3;
        average = total / 3.0;

        if (average >= 90)
            grade = "A";
        else if (average >= 75)
            grade = "B";
        else if (average >= 60)
            grade = "C";
        else if (average >= 50)
            grade = "D";
        else
            grade = "F";
    }

    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public double getAverage() {
        return average;
    }

    public String getGrade() {
        return grade;
    }
}


// VIEW
class StudentView extends JFrame {

    JTextField nameField, mark1Field, mark2Field, mark3Field;
    JButton calculateButton;
    JLabel totalLabel, averageLabel, gradeLabel;

    StudentView() {
        setTitle("Student Grade Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Student Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Subject 1 Marks:"));
        mark1Field = new JTextField();
        add(mark1Field);

        add(new JLabel("Subject 2 Marks:"));
        mark2Field = new JTextField();
        add(mark2Field);

        add(new JLabel("Subject 3 Marks:"));
        mark3Field = new JTextField();
        add(mark3Field);

        calculateButton = new JButton("Calculate Result");
        add(new JLabel(""));
        add(calculateButton);

        add(new JLabel("Total Marks:"));
        totalLabel = new JLabel("-");
        add(totalLabel);

        add(new JLabel("Average:"));
        averageLabel = new JLabel("-");
        add(averageLabel);

        add(new JLabel("Grade:"));
        gradeLabel = new JLabel("-");
        add(gradeLabel);

        setVisible(true);
    }
}


// CONTROLLER
class StudentController {

    StudentModel model;
    StudentView view;

    StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;

        view.calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
    }

    void calculateResult() {
        try {
            String name = view.nameField.getText();

            int m1 = Integer.parseInt(view.mark1Field.getText());
            int m2 = Integer.parseInt(view.mark2Field.getText());
            int m3 = Integer.parseInt(view.mark3Field.getText());

            if (name.isEmpty() || m1 < 0 || m1 > 100 ||
                    m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {

                JOptionPane.showMessageDialog(
                    view,
                    "Enter valid student details and marks between 0 and 100."
                );
                return;
            }

            model.calculate(name, m1, m2, m3);

            view.totalLabel.setText(String.valueOf(model.getTotal()));
            view.averageLabel.setText(
                String.format("%.2f", model.getAverage())
            );
            view.gradeLabel.setText(model.getGrade());

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                view,
                "Please enter valid numeric marks."
            );
        }
    }
}


// MAIN CLASS
public class StudentGradeCalculator {

    public static void main(String[] args) {

        StudentModel model = new StudentModel();
        StudentView view = new StudentView();

        new StudentController(model, view);
    }
}
