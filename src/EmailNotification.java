public class EmailNotification implements Notification {

    @Override
    public void send(String receiver, String message) {
        System.out.println("Connecting to email server...");
        System.out.println("Sending EMAIL to " + receiver);
        System.out.println("Message: " + message);
    }
}