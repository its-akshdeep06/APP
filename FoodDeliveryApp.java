class OrderProcessing extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " | Priority: " + getPriority()
                    + " | Processing customer order");
        }
    }
}

class DeliveryTracking extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " | Priority: " + getPriority()
                    + " | Tracking delivery location");
        }
    }
}

class Notification extends Thread {
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " | Priority: " + getPriority()
                    + " | Sending order-status notification");
        }
    }
}

public class FoodDeliveryApp {
    public static void main(String[] args) {

        // Create threads
        OrderProcessing order = new OrderProcessing();
        DeliveryTracking delivery = new DeliveryTracking();
        Notification notification = new Notification();

        // Set thread names
        order.setName("OrderProcessing");
        delivery.setName("DeliveryTracking");
        notification.setName("Notification");

        // Set priorities
        order.setPriority(Thread.MAX_PRIORITY);       // 10
        delivery.setPriority(Thread.NORM_PRIORITY);   // 5
        notification.setPriority(Thread.MIN_PRIORITY); // 1

        // Start all threads
        order.start();
        delivery.start();
        notification.start();
    }
}
