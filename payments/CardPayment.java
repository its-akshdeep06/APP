package payments;

public class CardPayment implements Payment, OnlineTransaction {
    private final String maskedCardNumber;

    public CardPayment(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid %.2f using card %s%n", amount, maskedCardNumber);
    }
}
