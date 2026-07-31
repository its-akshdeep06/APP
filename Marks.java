class Marks {
    int sub1, sub2, sub3;

    void setMarks(int m1, int m2, int m3) {
        sub1 = m1;
        sub2 = m2;
        sub3 = m3;
    }

    void calculate() {
        int total = sub1 + sub2 + sub3;
        double average = total / 3.0;

        System.out.println("Marks in Subject 1: " + sub1);
        System.out.println("Marks in Subject 2: " + sub2);
        System.out.println("Marks in Subject 3: " + sub3);
        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + average);
    }

    public static void main(String[] args) {
        Marks m = new Marks();

        m.setMarks(85, 90, 88);
        m.calculate();
    }
}