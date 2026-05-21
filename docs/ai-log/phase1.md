# Phase 1 AI Log

## Prompt

Bu kodda hangi tasarım sorunlarını görüyorsun? Hangi tasarım örüntüleri bu sorunları çözebilir? Her sorun için kısa bir açıklama yaz.

## AI Yanıtı

Bu kodda temel sorun, NotificationService sınıfının hangi bildirimin nasıl gönderileceğini kendisinin bilmesi ve yönetmesi. Bu da kod büyüdükçe bakımı zorlaştırır.

Tasarım sorunu	Açıklama	Çözebilecek örüntü / yaklaşım
Open/Closed Principle ihlali	Yeni bir bildirim tipi eklemek için NotificationService sınıfını değiştirmek gerekir. Örneğin WHATSAPP, SLACK, TELEGRAM eklenirse yeni else if blokları yazılır.	Strategy Pattern, Factory Method
Single Responsibility Principle ihlali	NotificationService hem bildirim tipine karar veriyor hem de gönderme işlemini yapıyor. Email, SMS ve Push gönderim detayları aynı sınıfta toplanmış.	Strategy Pattern, Dependency Injection
Uzun if-else zinciri	Kod büyüdükçe okunabilirlik azalır. Her yeni kanal yeni bir koşul ekler. Bu durum hata riskini artırır.	Strategy Pattern, Factory Pattern
String’e bağımlılık	"EMAIL", "SMS", "PUSH" gibi string değerler kırılgandır. Yazım hatası olursa kod çalışma zamanında hata verir veya yanlış davranır.	Enum, Factory Pattern
Tekrarlanan kod	Her blokta benzer şekilde “bağlan”, “gönder”, “mesaj yazdır” işlemleri var. Bu tekrarlar bakım maliyetini artırır.	Template Method, ortak arayüz kullanımı
Sıkı bağlılık	NotificationService, Email, SMS ve Push gönderim detaylarını doğrudan biliyor. Bu yüzden servis somut davranışlara bağımlı.	Dependency Injection, Strategy Pattern
Test edilebilirliğin düşük olması	System.out.println doğrudan kullanıldığı için gerçek gönderim mantığı ile çıktı üretimi karışmış. Birim test yazmak zorlaşır.	Dependency Injection, arayüz tabanlı tasarım
Genişlemeye kapalı mimari	Bildirim kanalları arttıkça sınıf şişer. Kod merkezi bir “kontrol noktası” haline gelir.	Strategy + Factory kombinasyonu

En uygun çözüm genellikle Strategy Pattern olur.

Örneğin her bildirim tipi ayrı bir sınıfa ayrılabilir:
## Ben Ne Uyguladım?

Bu senaryoda temel ihtiyaç, farklı bildirim tiplerine göre doğru gönderici nesnesinin oluşturulmasıdır. Bu nedenle Factory Pattern uygundur. Factory, if-else ile yapılan nesne seçme mantığını NotificationService sınıfından ayırır ve nesne oluşturma sorumluluğunu merkezi bir yapıya taşır. Böylece servis sınıfı somut bildirim tiplerini bilmek zorunda kalmaz.
## Neden Bu Çözümü Seçtim?

Başlangıç kodundaki en büyük problem, bildirim tiplerinin tek bir sınıfta if-else bloklarıyla yönetilmesiydi. Factory Method bu sorunu azaltarak nesne oluşturma sürecini merkezi ve daha okunabilir hale getirdi.

## AI'dan Farklı Yaptığım Noktalar

AI strategy pattern daha uygun olacağını söyledi. Ben factory pattern ile devam ettim.