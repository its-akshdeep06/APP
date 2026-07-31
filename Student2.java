class Student2 {
    String Name;
    int RollNo;
    String Dept;

    Student2(String Name, int RollNo, String Dept) {
        this.Name = Name;
        this.RollNo = RollNo;
        this.Dept = Dept;
    }

    void displayDetails() {
        System.out.println("Student Name: " + Name);
        System.out.println("Roll Number: " + RollNo);
        System.out.println("Department: " + Dept);
    }

    public static void main(String[] args) {
        Student2 student1 = new Student2("Aksh", 9, "CSE");
        student1.displayDetails();
    }
}