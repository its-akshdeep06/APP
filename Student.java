class Student {
    String Name;
    int RollNo;
    String Dept;

    Student(String Name, int RollNo, String Dept) {
        this.Name = Name;
        this.RollNo = RollNo;
        this.Dept = Dept;
    }

    public static void main(String[] args) {
        Student s = new Student("Aksh", 9, "CSE");
        System.out.println("Student 1: " + s.Name + "\nRoll No: " + s.RollNo + "\nDept: " + s.Dept);
    }
}
