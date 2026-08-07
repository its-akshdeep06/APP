import java.util.Scanner;

class Employee {
    int employeeId;
    String employeeName;
    double salary;

    // Constructor
    Employee(int id, String name, double sal) {
        this.employeeId = id;
        this.employeeName = name;
        this.salary = sal;
    }

    // Method to display employee details
    void display() {
        System.out.println("\nEmployee ID   : " + employeeId);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Salary        : " + salary);
    }
}

public class EmployeeDetails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee ID   : ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter Employee Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Salary        : ");
        double salary = sc.nextDouble();

        Employee emp = new Employee(id, name, salary);

        System.out.println("\nEmployee Details");
        System.out.println("----------------");
        emp.display();

        sc.close();
    }
}