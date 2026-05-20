# Başlangıç Kodundaki Tasarım Sorunları

## 1. Open/Closed Principle ihlali var

Sistem yeni bildirim türlerine açık değildir. Örneğin WhatsApp bildirimi eklemek istersek mevcut NotificationService sınıfını değiştirmemiz gerekir.

## 2. if-else zinciri büyümeye açık

Yeni bir bildirim tipi eklendiğinde sendNotification metoduna yeni bir else-if bloğu eklemek gerekir. Bu durum kodun okunabilirliğini azaltır.

## 3. Tip kontrolü string ile yapılıyor

Bildirim tipi "EMAIL", "SMS", "PUSH" gibi string değerlerle kontrol ediliyor. Yanlış yazım hataları derleme zamanında yakalanamaz.

## 4. Bildirim türleri aynı nesne olarak modellenmiş

Email, SMS ve Push bildirimleri ayrı sınıflar değildir. Bu yüzden her bildirim tipinin kendi davranışını bağımsız yönetmesi mümkün değildir.

## 5. NotificationService çok fazla sorumluluk taşıyor

NotificationService sınıfı hem bildirim tipini kontrol ediyor hem de Email, SMS ve Push gönderme işlemlerini yönetiyor. Bu durum sınıfın büyümesine ve bakımının zorlaşmasına neden olur.
