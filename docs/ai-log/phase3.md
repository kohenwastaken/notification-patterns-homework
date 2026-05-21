# Phase 3 AI Log

## Pair Programming Süresi

Bu fazda ChatGPT ile yaklaşık 30 dakika pair programming yapıldı. Amaç, bildirim sistemine behavioral design pattern eklemek ve sistemi Açık/Kapalı Prensibi'ne daha uygun hale getirmekti.

## Ne Tartışıldı?

Önce bildirim sistemi için hangi behavioral pattern'ların uygun olabileceği tartışıldı. Strategy, Observer ve Command seçenekleri değerlendirildi.

Strategy Pattern'in mesaj formatlama davranışı için uygun olduğuna karar verildi. Çünkü mesajlar plain text, uppercase, timestamp veya urgent formatında gönderilebilir.

Observer Pattern'in ise bildirim gönderildikten sonra audit log ve analytics gibi olayları tetiklemek için uygun olduğuna karar verildi.

## Uygulanan Çözüm

- `MessageFormatStrategy` arayüzü oluşturuldu.
- `PlainTextFormatStrategy`, `UpperCaseFormatStrategy`, `TimestampFormatStrategy` ve `UrgentFormatStrategy` sınıfları eklendi.
- `NotificationEventListener` arayüzü oluşturuldu.
- `ConsoleAuditListener` ve `AnalyticsListener` sınıfları eklendi.
- `NotificationEventPublisher` ile observer listesi yönetildi.
- `NotificationSender` sınıfı Strategy ve Observer yapılarını birlikte kullanacak şekilde tasarlandı.

## AI Olmadan Bu Faz Ne Kadar Sürerdi?

AI olmadan bu fazın yaklaşık 2-3 saat sürebileceğini düşünüyorum. Çünkü hangi behavioral pattern'ın projeye daha doğal oturacağını seçmek ve bu seçimleri OCP ile ilişkilendirmek daha fazla araştırma gerektirebilirdi.

## AI Nerede Yanılttı veya Eksik Kaldı?

AI ilk aşamada Command Pattern'i de önerebilirdi, ancak bu proje için Command Pattern biraz daha yapay kalabilirdi. Bildirim gönderme işlemini komut nesnelerine çevirmek mümkündü fakat asıl problemimiz davranış değiştirme ve olay sonrası tepki verme olduğu için Strategy ve Observer daha uygun oldu.

Ayrıca AI'ın verdiği önerileri doğrudan kullanmak yerine, projedeki mevcut Factory, Adapter ve Decorator yapılarıyla uyumlu olacak şekilde sadeleştirdim.