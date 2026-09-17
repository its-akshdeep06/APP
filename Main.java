import accounts.Account;
import accounts.CurrentAccount;
import accounts.SavingsAccount;
import coursemanagement.Course;
import payments.CardPayment;
import payments.OnlineTransaction;
import payments.Payment;
import payments.SecurePayment;
import payments.UPIPayment;
import studentmanagement.Student;

public class Main {
    public static void main(String[] args) {
        demonstrateCollegeManagement();
        demonstrateBankingSystem();
    }

    private static void demonstrateCollegeManagement() {
        Student student = new Student(101, "Aksh", "Computer Science");
        Course course = new Course("CS101", "Object-Oriented Programming", "Dr. Sharma", 4);

        System.out.println("===== STUDENT INFORMATION =====");
        student.displayInformation();

        System.out.println("\n===== COURSE INFORMATION =====");
        course.displayInformation();
    }

    private static void demonstrateBankingSystem() {
        System.out.println("\n===== ONLINE BANKING SYSTEM =====");

        Account[] accounts = {
            new SavingsAccount("SB1001", "Aksh", 25000.00, 4.5),
            new CurrentAccount("CA2001", "Aksh Enterprises", 75000.00, 50000.00)
        };

        System.out.println("\n--- Account Details ---");
        for (Account account : accounts) {
            account.displayDetails();
            System.out.println();
        }

        Payment[] payments = {
            new UPIPayment("aksh@upi"),
            new CardPayment("**** **** **** 1234")
        };

        System.out.println("--- Payment Details ---");
        for (Payment payment : payments) {
            if (payment instanceof SecurePayment securePayment) {
                securePayment.verifyPayment();
            }

            payment.pay(1500.00);

            if (payment instanceof OnlineTransaction) {
                System.out.println("Transaction identified as online.");
            }
            System.out.println();
        }
    }
}
