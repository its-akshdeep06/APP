import java.util.Scanner;

interface Payment {
    void makePayment(double amount);
}

class UPI implements Payment {
    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

class CreditCard implements Payment {
    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class NetBanking implements Payment {
    public void makePayment(double amount) {
        System.out.println("Paid " + amount + " using Net Banking");
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. UPI");
        System.out.println("2. Credit Card");
        System.out.println("3. Net Banking");

        System.out.print("Choose payment method: ");
        int choice = sc.nextInt();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        Payment payment;

        if (choice == 1) {
            payment = new UPI();
        } else if (choice == 2) {
            payment = new CreditCard();
        } else {
            payment = new NetBanking();
        }

        payment.makePayment(amount);
    }
}