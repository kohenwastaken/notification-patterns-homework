import java.util.ArrayList;
import java.util.List;

public class NotificationEventPublisher {

    private final List<NotificationEventListener> listeners = new ArrayList<>();

    public void subscribe(NotificationEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(NotificationEventListener listener) {
        listeners.remove(listener);
    }

    public void notifyNotificationSent(String type, String receiver, String message) {
        for (NotificationEventListener listener : listeners) {
            listener.onNotificationSent(type, receiver, message);
        }
    }
}