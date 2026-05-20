# Phase 1 AI Log

## Prompt

Bu kodda bildirim türleri if-else bloklarıyla yönetiliyor. Nesne oluşturma sorumluluğunu daha düzenli hale getirmek için hangi creational design pattern kullanılabilir? Factory Method bu örnek için uygun mu?

## AI Yanıt Özeti

AI, bu problem için Factory Method örüntüsünün uygun olduğunu belirtti. Çünkü Email, SMS ve Push gibi bildirim nesnelerinin oluşturulması tek bir yerde toplanabilir. Ayrıca her bildirim türünün ayrı sınıf olarak modellenmesi önerildi.

## Ben Ne Uyguladım?

Notification adında ortak bir interface oluşturdum. EmailNotification, SmsNotification ve PushNotification sınıfları bu interface'i uyguladı. Nesne oluşturma sorumluluğunu NotificationFactory sınıfına taşıdım.

## Neden Bu Çözümü Seçtim?

Başlangıç kodundaki en büyük problem, bildirim tiplerinin tek bir sınıfta if-else bloklarıyla yönetilmesiydi. Factory Method bu sorunu azaltarak nesne oluşturma sürecini merkezi ve daha okunabilir hale getirdi.

## AI'dan Farklı Yaptığım Noktalar

AI genel bir Factory yapısı önerdi. Ben kendi projemde bunu daha basit tuttum ve sadece mevcut bildirim türleri için uyguladım. Böylece Faz 1 kapsamını aşmadan, anlaşılır bir çözüm elde ettim.