# Phase 3 AI Log

## Prompt

bu sistem için 2 tane behavioral pattern kullanalım. neleri öneriyorsun?

## AI Yanıtı

Bence bu sistem için en mantıklı 2 behavioral pattern:

1. Command Pattern
2. Chain of Responsibility Pattern

Çünkü senin sistemin “bildirim gönderme” üzerine kurulu. Bu iki pattern, gerçek hayattaki notification sistemlerine çok güzel oturur.

Alternatif olarak Strategy de kullanılabilir mi?

Evet, ama senin mevcut sisteminde EmailNotification, SmsNotification, PushNotification, DiscordNotificationAdapter zaten biraz Strategy gibi davranıyor.

Çünkü hepsi aynı interface’i uyguluyor.

## Prompt

Strategy Pattern, mesaj formatlama davranışı için uygundur. Çünkü mesajlar plain text, uppercase, timestamp veya urgent formatında gönderilebilir. Ayrıca Observer Pattern bildirim gönderildikten sonra audit log ve analytics gibi olayları tetikleyebilir.

## AI Yanıtı

Evet, bu iki seçim gayet mantıklı. Hatta senin notification sistemi için Command + Chain of Responsibility yerine Strategy + Observer seçmek de oldukça savunulabilir.

Bence şöyle konumlandırabilirsin:

Strategy Pattern  -> Mesaj gönderilmeden önce nasıl formatlanacak?
Observer Pattern  -> Mesaj gönderildikten sonra kimler haberdar edilecek?
1. Strategy Pattern: Message Formatting

Mesajın gönderilme davranışı değil, mesajın formatlanma davranışı değişiyor.

Örneğin:

"System is down"

farklı strategy’lerle şöyle olabilir:

System is down
SYSTEM IS DOWN
[2026-05-21 14:30] System is down
URGENT: System is down !!!

Önce formatter interface’i:

public interface MessageFormatter {
String format(String message);
}

Plain text formatter:

public class PlainTextFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return message;
    }
}

Uppercase formatter:

public class UppercaseFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return message.toUpperCase();
    }
}

Urgent formatter:

public class UrgentFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return "URGENT: " + message + " !!!";
    }
}

Timestamp formatter:

import java.time.LocalDateTime;

public class TimestampFormatter implements MessageFormatter {

    @Override
    public String format(String message) {
        return "[" + LocalDateTime.now() + "] " + message;
    }
}

Sonra notification gönderirken formatter kullanabilirsin:

public class NotificationService {

    private MessageFormatter formatter;

    public NotificationService(MessageFormatter formatter) {
        this.formatter = formatter;
    }

    public void setFormatter(MessageFormatter formatter) {
        this.formatter = formatter;
    }

    public void send(Notification notification, String receiver, String message) {
        String formattedMessage = formatter.format(message);
        notification.send(receiver, formattedMessage);
    }
}

Kullanım:

NotificationFactory factory = new NotificationFactory();

Notification notification = factory.createNotification("EMAIL");

NotificationService service = new NotificationService(new UrgentFormatter());

service.send(notification, "yunus@example.com", "Server is down");

Burada NotificationService, mesajın nasıl formatlanacağını bilmez. Sadece MessageFormatter kullanır.

Bu yüzden bu kullanım Strategy Pattern için oldukça uygun.

2. Observer Pattern: Notification sonrası event tetikleme

Bildirim gönderildikten sonra bazı sistemlerin haberdar edilmesi gerekiyor olabilir:

Audit log yaz
Analytics event gönder
Monitoring sistemine bilgi ver
Admin paneline kayıt düş

Bunlar doğrudan Notification sınıflarının içine yazılırsa sistem kirlenir.

Kötü örnek:

public void send(String receiver, String message) {
sendEmail(receiver, message);
auditLogger.log(...);
analytics.track(...);
monitoring.notify(...);
}

Bu durumda EmailNotification çok fazla sorumluluk alır.

Bunun yerine Observer kullanabilirsin.

Önce observer interface’i:

public interface NotificationObserver {
void onNotificationSent(String type, String receiver, String message);
}

Audit log observer:

public class AuditLogObserver implements NotificationObserver {

    @Override
    public void onNotificationSent(String type, String receiver, String message) {
        System.out.println("AUDIT LOG: " + type + " sent to " + receiver);
    }
}

Analytics observer:

public class AnalyticsObserver implements NotificationObserver {

    @Override
    public void onNotificationSent(String type, String receiver, String message) {
        System.out.println("ANALYTICS: Notification type = " + type);
    }
}

Subject / publisher sınıfı:

import java.util.ArrayList;
import java.util.List;

public class NotificationEventManager {

    private List<NotificationObserver> observers = new ArrayList<>();

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String type, String receiver, String message) {
        for (NotificationObserver observer : observers) {
            observer.onNotificationSent(type, receiver, message);
        }
    }
}

Bunu NotificationService içine entegre edebilirsin:

public class NotificationService {

    private MessageFormatter formatter;
    private NotificationEventManager eventManager;

    public NotificationService(MessageFormatter formatter, NotificationEventManager eventManager) {
        this.formatter = formatter;
        this.eventManager = eventManager;
    }

    public void setFormatter(MessageFormatter formatter) {
        this.formatter = formatter;
    }

    public void send(String type, Notification notification, String receiver, String message) {
        String formattedMessage = formatter.format(message);

        notification.send(receiver, formattedMessage);

        eventManager.notifyObservers(type, receiver, formattedMessage);
    }
}

Kullanım:

NotificationFactory factory = new NotificationFactory();

Notification notification = factory.createNotification("DISCORD");

NotificationEventManager eventManager = new NotificationEventManager();

eventManager.addObserver(new AuditLogObserver());
eventManager.addObserver(new AnalyticsObserver());

NotificationService service = new NotificationService(
new TimestampFormatter(),
eventManager
);

service.send(
"DISCORD",
notification,
"general",
"Build completed successfully"
);
Sonuç

Senin önerdiğin iki pattern bu sistem için gayet iyi:

Strategy Pattern
Mesajın nasıl formatlanacağını değiştirir.

Observer Pattern
Bildirim gönderildikten sonra başka sistemleri haberdar eder.

Bence final tasarımın şöyle anlatılabilir:

Factory Pattern
Notification nesnelerini üretir.

Adapter Pattern
External Discord API'yi Notification interface'ine uydurur.

Strategy Pattern
Mesaj formatlama davranışını değiştirilebilir hale getirir.

Observer Pattern
Bildirim gönderildikten sonra audit log, analytics gibi sistemleri tetikler.

Bu kombinasyon gayet temiz:

Client
|
v
NotificationService
|
|-- uses MessageFormatter        -> Strategy
|
|-- uses NotificationFactory     -> Factory
|
|-- sends through Notification   -> Polymorphism
|
|-- DiscordNotificationAdapter   -> Adapter
|
|-- notifies observers           -> Observer

Yani evet: Strategy + Observer bu senaryo için oldukça uygun ve akademik olarak da güzel savunulur.


## Ne Tartışıldı?

Önce bildirim sistemi için hangi behavioral pattern'ların uygun olabileceği tartışıldı. Yapay zeka, Command ve Chain of Responsibility Patternlarını önerdi. Ama tarafımdan Strategy ve Observer patternları önerildi.

## Uygulanan Çözüm

- `MessageFormatStrategy` arayüzü oluşturuldu.
- `PlainTextFormatStrategy`, `UpperCaseFormatStrategy`, `TimestampFormatStrategy` ve `UrgentFormatStrategy` sınıfları eklendi.
- `NotificationEventListener` arayüzü oluşturuldu.
- `ConsoleAuditListener` ve `AnalyticsListener` sınıfları eklendi.
- `NotificationEventPublisher` ile observer listesi yönetildi.
- `NotificationSender` sınıfı Strategy ve Observer yapılarını birlikte kullanacak şekilde tasarlandı.

## AI Olmadan Bu Faz Ne Kadar Sürerdi?

Yapay zeka kullanmadan sadece bu faz kendi başına iyi senaryoda saatler sürecektir. Doğru patternları seçmemiz konusunda yapay zekanın sunduğu çözüm yöntemlerini örneklemesi bize fazlasıya zaman kazandırır. 

## AI Nerede Yanılttı veya Eksik Kaldı?

Yapay zeka, mevcut sistemimizde doğal genişleme yerlerini merkeze alamadı. elimizdeki mesajları formatlama, log-analytcis yerine direkt daha karmaşık olan kuyruklama ve kanal değiştirme gibi seçeneklere atladı. command ve chain of responsibility nin yanlış olduğunu düşünmüyorum ama önceliğimiz değildi.