public class ConsoleAuditListener implements NotificationEventListener {

    @Override
    public void onNotificationSent(String type, String receiver, String message) {
        System.out.println("[AUDIT] Notification sent. Type: " + type + ", Receiver: " + receiver);
    }
}