public class LoggingNotificationDecorator extends NotificationDecorator {

    public LoggingNotificationDecorator(Notification wrappedNotification) {
        super(wrappedNotification);
    }

    @Override
    public void send(String receiver, String message) {
        System.out.println("[LOG] Notification sending started.");
        wrappedNotification.send(receiver, message);
        System.out.println("[LOG] Notification sending completed.");
    }
}