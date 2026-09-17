public class BankingActivities7 {
    private static class BankingTask implements Runnable {
        private final String activity;
        private final int delay;

        BankingTask(String activity, int delay) {
            this.activity = activity;
            this.delay = delay;
        }

        @Override
        public void run() {
            for (int count = 1; count <= 3; count++) {
                System.out.println(Thread.currentThread().getName() + " - " + activity
                        + " - execution " + count);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread transaction = new Thread(new BankingTask("Processing transaction", 350));
        Thread balance = new Thread(new BankingTask("Updating balance", 500));
        Thread sms = new Thread(new BankingTask("Sending SMS notification", 650));

        transaction.setName("Transaction-Processor");
        balance.setName("Balance-Updater");
        sms.setName("SMS-Notifier");

        transaction.start();
        balance.start();
        sms.start();
        transaction.join();
        balance.join();
        sms.join();
    }
}
