import java.util.Scanner;

class Car {
    String model;
    double price;

    // Constructor 1: No details
    Car() {
        this.model = "Not Specified";
        this.price = 0.0;
    }

    // Constructor 2: Only model name
    Car(String model) {
        this.model = model;
        this.price = 0.0;
    }

    // Constructor 3: Model name and price
    Car(String model, double price) {
        this.model = model;
        this.price = price;
    }

    void display() {
        System.out.println("\n--- Car Details ---");
        System.out.println("Model : " + model);
        System.out.println("Price : ₹" + price);
    }
}

public class CarShowroom {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose registration type:");
        System.out.println("1. No details");
        System.out.println("2. Model name only");
        System.out.println("3. Model name and price");
        System.out.print("Enter choice (1/2/3): ");
        int choice = sc.nextInt();
        sc.nextLine();

        Car car;

        if (choice == 1) {
            car = new Car();
        } else if (choice == 2) {
            System.out.print("Enter Model Name: ");
            String model = sc.nextLine();
            car = new Car(model);
        } else {
            System.out.print("Enter Model Name : ");
            String model = sc.nextLine();
            System.out.print("Enter Price      : ₹");
            double price = sc.nextDouble();
            car = new Car(model, price);
        }

        car.display();
    }
}