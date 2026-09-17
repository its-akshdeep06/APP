package accounts;

public class SavingsAccount extends Account {
    private final double interestRate;

    public SavingsAccount(String accountNumber, String accountHolderName, double balance, double interestRate) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void displayDetails() {
        System.out.println("Savings Account");
        super.displayDetails();
        System.out.printf("Interest Rate: %.2f%%%n", interestRate);
    }
}
