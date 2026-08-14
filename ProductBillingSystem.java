import java.util.Scanner;

class Product {
    int id, quantity;
    String name;
    double price;

    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    void display() {
        double total = price * quantity;
        double discount;

        if (total >= 5000)
            discount = total * 0.10;
        else
            discount = total * 0.05;

        double finalPrice = total - discount;

        System.out.println("Product ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Total Price: ₹" + total);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Price: ₹" + finalPrice);
        System.out.println();
    }
}

public class ProductBillingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] p = new Product[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Product " + (i + 1) + ":");

            System.out.print("ID: ");
            int id = sc.nextInt();

            System.out.print("Name: ");
            String name = sc.next();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            System.out.print("Quantity: ");
            int quantity = sc.nextInt();

            p[i] = new Product(id, name, price, quantity);
            System.out.println();
        }

        for (int i = 0; i < 5; i++) {
            p[i].display();
        }

        sc.close();
    }
}