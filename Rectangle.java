class Rectangle {
    double length;
    double breadth;

    Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    void calculateArea() {
        double area = length * breadth;
        System.out.println("Area of Rectangle = " + area);
    }

    public static void main(String[] args) {
        Rectangle room = new Rectangle(10.5, 8.0);
        room.calculateArea();
    }
}