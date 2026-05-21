public interface NotificationEventListener {
    void onNotificationSent(String type, String receiver, String message);
}