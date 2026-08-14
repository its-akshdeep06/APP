import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int[] marks;
    double attendance;

    Student(int rollNo, String name, int[] marks, double attendance) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        this.attendance = attendance;
    }

    double getAverage() {
        int total = 0;

        for (int i = 0; i < 3; i++) {
            total += marks[i];
        }

        return total / 3.0;
    }

    void display() {
        int total = 0;

        for (int i = 0; i < 3; i++) {
            total += marks[i];
        }

        double average = total / 3.0;

        String result = average >= 50 ? "Pass" : "Fail";
        String scholarship = average >= 75 && attendance >= 80 ? "Eligible" : "Not Eligible";
        String performance = average >= 85 ? "Excellent" : "Good";

        System.out.println("Roll Number: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Total Marks: " + total);
        System.out.println("Average: " + average);
        System.out.println("Result: " + result);
        System.out.println("Scholarship: " + scholarship);
        System.out.println("Performance: " + performance);
        System.out.println("Attendance: " + attendance + "%");
    }
}

public class StudentPerformance {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student[] students = new Student[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Student " + (i + 1));

            System.out.print("Roll Number: ");
            int rollNo = sc.nextInt();

            System.out.print("Name: ");
            String name = sc.next();

            int[] marks = new int[3];

            for (int j = 0; j < 3; j++) {
                System.out.print("Mark " + (j + 1) + ": ");
                marks[j] = sc.nextInt();
            }

            System.out.print("Attendance: ");
            double attendance = sc.nextDouble();

            students[i] = new Student(rollNo, name, marks, attendance);
            System.out.println();
        }

        for (int i = 0; i < 5; i++) {
            students[i].display();
        }

        int highest = 0;

        for (int i = 1; i < 5; i++) {
            highest = students[i].getAverage() > students[highest].getAverage()
                    ? i : highest;
        }

        System.out.println("Student with Highest Average:");
        System.out.println("Roll Number: " + students[highest].rollNo);
        System.out.println("Name: " + students[highest].name);
        System.out.println("Average: " + students[highest].getAverage());

        sc.close();
    }
}