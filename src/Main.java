public class Main {
    public static void main(String[] args) {

        NotificationFactory factory = new NotificationFactory();

        NotificationEventPublisher eventPublisher = new NotificationEventPublisher();
        eventPublisher.subscribe(new ConsoleAuditListener());
        eventPublisher.subscribe(new AnalyticsListener());

        NotificationSender sender = new NotificationSender(
                factory,
                new PlainTextFormatStrategy(),
                eventPublisher
        );

        sender.send("EMAIL", "user@example.com", "Welcome to the system!");

        sender.setMessageFormatStrategy(new UpperCaseFormatStrategy());
        sender.send("SMS", "05551234567", "Your verification code is 1234");

        sender.setMessageFormatStrategy(new TimestampFormatStrategy());
        sender.send("DISCORD", "#announcements", "Deployment completed successfully.");

        sender.setMessageFormatStrategy(new UrgentFormatStrategy());
        sender.send("PUSH", "user123", "Server CPU usage is high.");

        Notification decoratedNotification =
                new LoggingNotificationDecorator(
                        new PriorityNotificationDecorator(
                                factory.createNotification("EMAIL")
                        )
                );

        decoratedNotification.send("admin@example.com", "System health check completed.");
    }
}