public class NotificationFactory {

    public Notification createNotification(String type) {

        if (type.equalsIgnoreCase("EMAIL")) {
            return new EmailNotification();
        }

        if (type.equalsIgnoreCase("SMS")) {
            return new SmsNotification();
        }

        if (type.equalsIgnoreCase("PUSH")) {
            return new PushNotification();
        }

        if (type.equalsIgnoreCase("DISCORD")) {
            return new DiscordNotificationAdapter(
                    new ExternalDiscordApi(),
                    "Software Engineering Server"
            );
        }

        throw new IllegalArgumentException("Unknown notification type: " + type);
    }
}