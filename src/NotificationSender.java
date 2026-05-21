public class NotificationSender {

    private final NotificationFactory notificationFactory;
    private final NotificationEventPublisher eventPublisher;
    private MessageFormatStrategy messageFormatStrategy;

    public NotificationSender(
            NotificationFactory notificationFactory,
            MessageFormatStrategy messageFormatStrategy,
            NotificationEventPublisher eventPublisher
    ) {
        this.notificationFactory = notificationFactory;
        this.messageFormatStrategy = messageFormatStrategy;
        this.eventPublisher = eventPublisher;
    }

    public void setMessageFormatStrategy(MessageFormatStrategy messageFormatStrategy) {
        this.messageFormatStrategy = messageFormatStrategy;
    }

    public void send(String notificationType, String receiver, String message) {
        Notification notification = notificationFactory.createNotification(notificationType);
        String formattedMessage = messageFormatStrategy.format(message);

        notification.send(receiver, formattedMessage);
        eventPublisher.notifyNotificationSent(notificationType, receiver, formattedMessage);
    }
}