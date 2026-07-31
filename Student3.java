class Student3 {
    String name;
    int age;

    void setDetails(String n, int a) {
        name = n;
        age = a;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println();
    }

    public static void main(String[] args) {
        Student3 s1 = new Student3();
        Student3 s2 = new Student3();

        s1.setDetails("Akshdeep", 19);
        s2.setDetails("Rahul", 20);

        System.out.println("Student 1 Details:");
        s1.display();

        System.out.println("Student 2 Details:");
        s2.display();
    }
}