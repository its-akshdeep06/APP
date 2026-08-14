class Student1 {
    String Name;
    int RollNo;
    String Dept;

    Student1(String Name, int RollNo, String Dept) {
        this.Name = Name;
        this.RollNo = RollNo;
        this.Dept = Dept;
    }

    public static void main(String[] args) {
        Student1 s = new Student1("Aksh", 9, "CSE");
        System.out.println("Student 1: " + s.Name + "\nRoll No: " + s.RollNo + "\nDept: " + s.Dept);
    }
}
