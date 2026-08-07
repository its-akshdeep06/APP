import java.util.Scanner;

class Geometry {
    // Area of Square
    double area(double side) {
        return side * side;
    }

    // Area of Rectangle
    double area(double length, double breadth) {
        return length * breadth;
    }

    // Area of Circle
    double area(double radius, boolean isCircle) {
        return Math.PI * radius * radius;
    }
}

public class GeometryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Geometry g = new Geometry();

        System.out.println("Choose shape:");
        System.out.println("1. Square");
        System.out.println("2. Rectangle");
        System.out.println("3. Circle");
        System.out.print("Enter choice (1/2/3): ");
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.print("Enter side of square: ");
            double side = sc.nextDouble();
            System.out.printf("Area of Square    : %.2f%n", g.area(side));

        } else if (choice == 2) {
            System.out.print("Enter length    : ");
            double length = sc.nextDouble();
            System.out.print("Enter breadth   : ");
            double breadth = sc.nextDouble();
            System.out.printf("Area of Rectangle : %.2f%n", g.area(length, breadth));

        } else {
            System.out.print("Enter radius of circle: ");
            double radius = sc.nextDouble();
            System.out.printf("Area of Circle    : %.2f%n", g.area(radius, true));
        }
    }
}