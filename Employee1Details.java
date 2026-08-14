import java.util.Scanner;

class Employee1 {
    int Employee1Id;
    String Employee1Name;
    double salary;

    // Constructor
    Employee1(int id, String name, double sal) {
        this.Employee1Id = id;
        this.Employee1Name = name;
        this.salary = sal;
    }

    // Method to display Employee1 details
    void display() {
        System.out.println("\nEmployee1 ID   : " + Employee1Id);
        System.out.println("Employee1 Name : " + Employee1Name);
        System.out.println("Salary        : " + salary);
    }
}

public class Employee1Details {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Employee1 ID   : ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter Employee1 Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Salary        : ");
        double salary = sc.nextDouble();

        Employee1 emp = new Employee1(id, name, salary);

        System.out.println("\nEmployee1 Details");
        System.out.println("----------------");
        emp.display();

        sc.close();
    }
}