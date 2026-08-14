import java.util.Scanner;

class Patient {
    String patientName;
    double consultationFee;

    Patient(String patientName, double consultationFee) {
        this.patientName = patientName;
        this.consultationFee = consultationFee;
    }

    double calculateFinalAmount(double fee) {
        if (fee >= 2000) {
            return fee - (fee * 0.10);
        } else {
            return fee - (fee * 0.05);
        }
    }

    void displayDetails() {
        double discount;

        if (consultationFee >= 2000) {
            discount = consultationFee * 0.10;
        } else {
            discount = consultationFee * 0.05;
        }

        double finalAmount = calculateFinalAmount(consultationFee);

        System.out.println("Patient Name: " + patientName);
        System.out.println("Original Consultation Fee: ₹" + consultationFee);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);
        System.out.println("-----------------------------------");
    }
}

public class HospitalBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Patient[] patients = new Patient[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("\nPatient " + (i + 1));

            System.out.print("Patient Name: ");
            String name = sc.next();

            System.out.print("Consultation Fee: ");
            double fee = sc.nextDouble();

            patients[i] = new Patient(name, fee);
        }

        System.out.println("\n========== CONSULTATION BILLING ==========");

        for (int i = 0; i < 5; i++) {
            patients[i].displayDetails();
        }

        sc.close();
    }
}