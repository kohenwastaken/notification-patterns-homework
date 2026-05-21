public class PriorityNotificationDecorator extends NotificationDecorator {

    public PriorityNotificationDecorator(Notification wrappedNotification) {
        super(wrappedNotification);
    }

    @Override
    public void send(String receiver, String message) {
        String priorityMessage = "[HIGH PRIORITY] " + message;
        wrappedNotification.send(receiver, priorityMessage);
    }
}