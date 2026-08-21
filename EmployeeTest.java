import java.util.Scanner;

class Employee {
    String name;
    int employeeId;
    double basicSalary;

    Employee(String name, int employeeId, double basicSalary) {
        this.name = name;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
    }

    double calculateSalary() {
        return basicSalary;
    }
}

class Professor extends Employee {
    Professor(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    double calculateSalary() {
        return basicSalary + basicSalary * 0.20;
    }
}

class LabAssistant extends Employee {
    LabAssistant(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    double calculateSalary() {
        return basicSalary + basicSalary * 0.10;
    }
}

class AdministrativeStaff extends Employee {
    AdministrativeStaff(String name, int employeeId, double basicSalary) {
        super(name, employeeId, basicSalary);
    }

    @Override
    double calculateSalary() {
        return basicSalary + basicSalary * 0.15;
    }
}

public class EmployeeTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter employee type (1-Professor, 2-Lab Assistant, 3-Administrative Staff): ");
        int type = sc.nextInt();

        sc.nextLine(); // consume newline

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter employee ID: ");
        int employeeId = sc.nextInt();

        System.out.print("Enter basic salary: ");
        double basicSalary = sc.nextDouble();

        Employee employee;

        if (type == 1) {
            employee = new Professor(name, employeeId, basicSalary);
        } 
        else if (type == 2) {
            employee = new LabAssistant(name, employeeId, basicSalary);
        } 
        else if (type == 3) {
            employee = new AdministrativeStaff(name, employeeId, basicSalary);
        } 
        else {
            System.out.println("Invalid employee type!");
            sc.close();
            return;
        }

        System.out.println("\n--- Employee Details ---");
        System.out.println("Name: " + employee.name);
        System.out.println("Employee ID: " + employee.employeeId);
        System.out.println("Basic Salary: " + employee.basicSalary);
        System.out.println("Calculated Salary: " + employee.calculateSalary());

        sc.close();
    }
}
