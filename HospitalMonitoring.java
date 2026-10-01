class EmergencyAlert extends Thread {
    public void run() {
        System.out.println("Thread Name: " + getName());
        System.out.println("Priority: " + getPriority());
        System.out.println("Checking emergency alerts...");
    }
}

class VitalMonitor extends Thread {
    public void run() {
        System.out.println("Thread Name: " + getName());
        System.out.println("Priority: " + getPriority());
        System.out.println("Monitoring vital signs...");
    }
}

class ReportGenerator extends Thread {
    public void run() {
        System.out.println("Thread Name: " + getName());
        System.out.println("Priority: " + getPriority());
        System.out.println("Generating routine report...");
    }
}

public class HospitalMonitoring {
    public static void main(String[] args) {

        EmergencyAlert emergency = new EmergencyAlert();
        VitalMonitor vital = new VitalMonitor();
        ReportGenerator report = new ReportGenerator();

        // Set thread names
        emergency.setName("EmergencyAlert");
        vital.setName("VitalMonitor");
        report.setName("ReportGenerator");

        // Set priorities
        emergency.setPriority(Thread.MAX_PRIORITY); // 10
        vital.setPriority(Thread.NORM_PRIORITY);    // 5
        report.setPriority(Thread.MIN_PRIORITY);    // 1

        // Start threads
        emergency.start();
        vital.start();
        report.start();
    }
}
