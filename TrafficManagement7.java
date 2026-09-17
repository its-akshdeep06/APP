public class TrafficManagement7 {
    private static class TrafficJunction extends Thread {
        private final String status;
        private final int delay;

        TrafficJunction(String name, String status, int delay) {
            super(name);
            this.status = status;
            this.delay = delay;
        }

        @Override
        public void run() {
            for (int count = 1; count <= 3; count++) {
                System.out.println(getName() + " - Traffic status: " + status
                        + " - report " + count);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException exception) {
                    interrupt();
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread junctionA = new TrafficJunction("Junction-A", "Heavy", 400);
        Thread junctionB = new TrafficJunction("Junction-B", "Moderate", 600);
        Thread junctionC = new TrafficJunction("Junction-C", "Light", 800);

        junctionA.start();
        junctionB.start();
        junctionC.start();
        junctionA.join();
        junctionB.join();
        junctionC.join();
    }
}
