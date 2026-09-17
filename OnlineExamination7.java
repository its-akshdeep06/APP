public class OnlineExamination7 {
    private static class ExaminationTask implements Runnable {
        private final String activity;
        private final int delay;

        ExaminationTask(String activity, int delay) {
            this.activity = activity;
            this.delay = delay;
        }

        @Override
        public void run() {
            for (int count = 1; count <= 3; count++) {
                System.out.println(Thread.currentThread().getName() + " - " + activity);
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
        Thread timer = new Thread(new ExaminationTask("Displaying remaining time", 400));
        Thread autoSave = new Thread(new ExaminationTask("Auto-saving student answers", 600));
        Thread network = new Thread(new ExaminationTask("Checking network connection", 800));

        timer.setName("Exam-Timer");
        autoSave.setName("Answer-AutoSave");
        network.setName("Network-Checker");

        timer.start();
        autoSave.start();
        network.start();
        timer.join();
        autoSave.join();
        network.join();
    }
}
