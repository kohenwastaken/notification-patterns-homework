public class NotificationService {

    public void sendNotification(String type, String receiver, String message) {

        if (type.equals("EMAIL")) {
            System.out.println("Connecting to email server...");
            System.out.println("Sending EMAIL to " + receiver);
            System.out.println("Message: " + message);
        }
        else if (type.equals("SMS")) {
            System.out.println("Connecting to SMS gateway...");
            System.out.println("Sending SMS to " + receiver);
            System.out.println("Message: " + message);
        }
        else if (type.equals("PUSH")) {
            System.out.println("Connecting to push service...");
            System.out.println("Sending PUSH notification to " + receiver);
            System.out.println("Message: " + message);
        }
        else {
            System.out.println("Unknown notification type!");
        }
    }
}