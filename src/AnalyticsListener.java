public class AnalyticsListener implements NotificationEventListener {

    @Override
    public void onNotificationSent(String type, String receiver, String message) {
        System.out.println("[ANALYTICS] Counting notification event for type: " + type);
    }
}