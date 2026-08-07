import java.util.Scanner;

public class Supermarket {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] prices = new double[5];
        double total = 0;

        System.out.println("Enter prices of 5 products:");
        for (int i = 0; i < 5; i++) {
            System.out.print("Product " + (i + 1) + ": ");
            prices[i] = sc.nextDouble();
        }

        for (int i = 0; i < 5; i++) {
            total += prices[i];
        }

        System.out.println("\nTotal Bill : " + (int) total);
        if (total > 5000) {
            System.out.println("Discount Applicable");
        } else {
            System.out.println("No Discount");
        }
    }
}