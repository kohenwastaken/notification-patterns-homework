public class PushNotification implements Notification {

    @Override
    public void send(String receiver, String message) {
        System.out.println("Connecting to push notification service...");
        System.out.println("Sending PUSH notification to " + receiver);
        System.out.println("Message: " + message);
    }
}