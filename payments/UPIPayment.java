package payments;

public class UPIPayment implements SecurePayment, OnlineTransaction {
    private final String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void verifyPayment() {
        System.out.println("UPI payment verified for: " + upiId);
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid %.2f using UPI ID %s%n", amount, upiId);
    }
}
