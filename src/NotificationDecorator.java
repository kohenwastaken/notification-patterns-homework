public abstract class NotificationDecorator implements Notification {

    protected Notification wrappedNotification;

    public NotificationDecorator(Notification wrappedNotification) {
        this.wrappedNotification = wrappedNotification;
    }

    @Override
    public void send(String receiver, String message) {
        wrappedNotification.send(receiver, message);
    }
}