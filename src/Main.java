public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();

        service.sendNotification("EMAIL", "user@example.com", "Welcome!");
        service.sendNotification("SMS", "05551234567", "Your code is 1234");
        service.sendNotification("PUSH", "user123", "You have a new message");
    }
}