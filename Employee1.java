class Employee1 {
    int empId;
    String name;
    double salary;

    void setDetails(int id, String n, double s) {
        empId = id;
        name = n;
        salary = s;
    }

    void display() {
        System.out.println("Employee1 ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println();
    }

    public static void main(String[] args) {
        Employee1 e1 = new Employee1();
        Employee1 e2 = new Employee1();

        e1.setDetails(101, "Akshdeep", 550000);
        e2.setDetails(102, "Priya", 5000);

        System.out.println("Employee1 1 Details:");
        e1.display();

        System.out.println("Employee1 2 Details:");
        e2.display();
    }
}