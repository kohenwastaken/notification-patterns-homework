public class SmsNotification implements Notification {

    @Override
    public void send(String receiver, String message) {
        System.out.println("Connecting to SMS gateway...");
        System.out.println("Sending SMS to " + receiver);
        System.out.println("Message: " + message);
    }
}