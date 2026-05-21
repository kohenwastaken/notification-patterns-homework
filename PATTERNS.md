# Kullanılan Tasarım Örüntüleri

## Phase 1 - Factory Method

### Nerede kullanıldı?

Factory Method örüntüsü `NotificationFactory` sınıfında kullanıldı. Email, SMS ve Push bildirim nesneleri doğrudan `Main` içinde oluşturulmak yerine factory sınıfı üzerinden oluşturuldu.

### Neden kullanıldı?

Başlangıç kodunda bildirim türleri `NotificationService` içinde `if-else` bloklarıyla yönetiliyordu. Yeni bir bildirim türü eklemek istediğimizde aynı sınıfı değiştirmek gerekiyordu. Bu durum kodun büyümesine ve bakımının zorlaşmasına neden oluyordu.

### Ne kazandırdı?

Nesne oluşturma sorumluluğu merkezi hale getirildi. Her bildirim tipi ayrı sınıfa taşındı. Böylece kod daha okunabilir, test edilebilir ve genişletilebilir oldu.

### Kapatılan problem

Bu örüntü özellikle `PROBLEMS.md` dosyasındaki şu sorunları azaltmıştır:

- if-else zincirinin büyümesi
- String ile tip kontrolünün riskli olması
- Bildirim türlerinin ayrı nesneler olarak modellenmemesi
- NotificationService sınıfının fazla sorumluluk taşıması


## Phase 2 - Adapter Pattern

### Nerede kullanıldı?

Adapter Pattern `DiscordNotificationAdapter` sınıfında kullanıldı. `ExternalDiscordApi` sınıfı mevcut `Notification` interface'ine uymadığı için adapter aracılığıyla sisteme dahil edildi.

### Neden kullanıldı?

Mevcut sistem tüm bildirim türlerinin `send(String receiver, String message)` metodunu kullanmasını bekliyordu. Ancak dış Discord servisi `sendDiscordMessage(String serverName, String channelName, String content)` metodunu kullanıyordu. Adapter Pattern bu uyumsuzluğu çözmek için seçildi.

### Ne kazandırdı?

Dış Discord servisi mevcut bildirim sistemine minimum değişiklikle dahil edildi. Ana bildirim arayüzü korunarak sistemin farklı servislerle çalışması sağlandı.

---

## Phase 2 - Decorator Pattern

### Nerede kullanıldı?

Decorator Pattern `NotificationDecorator`, `LoggingNotificationDecorator` ve `PriorityNotificationDecorator` sınıflarında kullanıldı.

### Neden kullanıldı?

Bildirimlere loglama veya öncelik etiketi gibi ek davranışlar eklemek istendi. Bu davranışları `EmailNotification`, `SmsNotification` veya `PushNotification` sınıflarının içine yazmak kod tekrarına ve sınıfların büyümesine neden olacaktı.

### Ne kazandırdı?

Mevcut bildirim sınıfları değiştirilmeden yeni özellikler eklendi. Bildirim nesneleri çalışma zamanında farklı decorator'lar ile genişletilebilir hale geldi.


---

## Phase 3 - Strategy Pattern

### Nerede kullanıldı?

Strategy Pattern mesaj formatlama davranışlarında kullanıldı. `MessageFormatStrategy` ortak arayüz olarak tanımlandı. `PlainTextFormatStrategy`, `UpperCaseFormatStrategy`, `TimestampFormatStrategy` ve `UrgentFormatStrategy` bu arayüzü uyguladı.

### Neden kullanıldı?

Mesajların farklı formatlarda gönderilebilmesi gerekiyordu. Bu davranışı `NotificationSender` içine if-else bloklarıyla yazmak kodun büyümesine ve değiştirilmesine neden olurdu.

### Ne kazandırdı?

Mesaj formatlama algoritması çalışma zamanında değiştirilebilir hale geldi. Yeni bir formatlama davranışı eklemek için mevcut `NotificationSender` sınıfı değiştirilmeden yeni bir strategy sınıfı eklenebilir.

### OCP Gösterimi

`UrgentFormatStrategy` sınıfı sisteme sonradan eklendi. Bu davranış eklenirken `NotificationSender` değiştirilmedi. Böylece sistem yeni davranışlara açık, mevcut kod değişikliklerine kapalı hale getirildi.

---

## Phase 3 - Observer Pattern

### Nerede kullanıldı?

Observer Pattern `NotificationEventPublisher` ve `NotificationEventListener` yapısında kullanıldı. `ConsoleAuditListener` ve `AnalyticsListener`, bildirim gönderme olaylarını dinleyen sınıflar olarak eklendi.

### Neden kullanıldı?

Bildirim gönderildikten sonra audit log veya analytics gibi ek işlemler yapılmak istendi. Bu işlemleri doğrudan bildirim sınıflarına yazmak sınıfların sorumluluğunu artırırdı.

### Ne kazandırdı?

Bildirim gönderme işlemi ile bildirim sonrası yapılacak işlemler birbirinden ayrıldı. Yeni bir dinleyici eklemek için mevcut bildirim sınıflarını değiştirmeye gerek kalmadı.