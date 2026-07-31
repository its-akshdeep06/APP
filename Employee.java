class Employee {
    int empId;
    String name;
    double salary;

    void setDetails(int id, String n, double s) {
        empId = id;
        name = n;
        salary = s;
    }

    void display() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println();
    }

    public static void main(String[] args) {
        Employee e1 = new Employee();
        Employee e2 = new Employee();

        e1.setDetails(101, "Akshdeep", 550000);
        e2.setDetails(102, "Priya", 5000);

        System.out.println("Employee 1 Details:");
        e1.display();

        System.out.println("Employee 2 Details:");
        e2.display();
    }
}