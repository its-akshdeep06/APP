class vehicle6 {
    String vehicle6Number;
    String brand;
    int speed;

    vehicle6(String vehicle6Number, String brand, int speed) {
        this.vehicle6Number = vehicle6Number;
        this.brand = brand;
        this.speed = speed;
    }

    void displayDetails() {
        System.out.println("vehicle6 Number: " + vehicle6Number);
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed);
    }
}

class Car extends vehicle6 {
    int numberOfDoors;

    Car(String vehicle6Number, String brand, int speed, int numberOfDoors) {
        super(vehicle6Number, brand, speed);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    void displayDetails() {
        System.out.println("Car");
        System.out.println("vehicle6 Number: " + vehicle6Number);
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed);
        System.out.println("Number of Doors: " + numberOfDoors);
    }
}

class Bike extends vehicle6 {
    boolean hasGear;

    Bike(String vehicle6Number, String brand, int speed, boolean hasGear) {
        super(vehicle6Number, brand, speed);
        this.hasGear = hasGear;
    }

    @Override
    void displayDetails() {
        System.out.println("Bike");
        System.out.println("vehicle6 Number: " + vehicle6Number);
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed);
        System.out.println("Has Gear: " + hasGear);
    }
}

public class vehicle6 {
    public static void main(String[] args) {
        vehicle6 v;

        v = new Car("TN01AB1234", "BMW", 180, 4);
        v.displayDetails();

        System.out.println();

        v = new Bike("TN02CD5678", "Yamaha", 120, true);
        v.displayDetails();
    }
}