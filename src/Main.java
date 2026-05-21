public class Main {
    public static void main(String[] args) {

        NotificationFactory factory = new NotificationFactory();

        Notification emailNotification = factory.createNotification("EMAIL");
        emailNotification.send("user@example.com", "Welcome to the system!");

        Notification smsNotification = factory.createNotification("SMS");
        smsNotification.send("05551234567", "Your verification code is 1234");

        Notification pushNotification = factory.createNotification("PUSH");
        pushNotification.send("user123", "You have a new message");

        Notification discordNotification = factory.createNotification("DISCORD");
        discordNotification.send("#announcements", "Deployment completed successfully.");

        Notification loggedEmailNotification =
                new LoggingNotificationDecorator(factory.createNotification("EMAIL"));

        loggedEmailNotification.send("admin@example.com", "System health check completed.");

        Notification prioritySmsNotification =
                new PriorityNotificationDecorator(factory.createNotification("SMS"));

        prioritySmsNotification.send("05559876543", "Server CPU usage is high.");
    }
}