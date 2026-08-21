import java.util.Scanner;

public class ShapeAreaCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Circle");
        System.out.println("2. Rectangle");
        System.out.println("3. Triangle");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        double area;

        if (choice == 1) {
            System.out.print("Enter radius: ");
            double r = sc.nextDouble();

            area = Math.PI * r * r;
        }
        else if (choice == 2) {
            System.out.print("Enter length: ");
            double l = sc.nextDouble();

            System.out.print("Enter width: ");
            double w = sc.nextDouble();

            area = l * w;
        }
        else if (choice == 3) {
            System.out.print("Enter base: ");
            double b = sc.nextDouble();

            System.out.print("Enter height: ");
            double h = sc.nextDouble();

            area = 0.5 * b * h;
        }
        else {
            System.out.println("Invalid choice!");
            return;
        }

        System.out.println("Area = " + area);

        sc.close();
    }
}