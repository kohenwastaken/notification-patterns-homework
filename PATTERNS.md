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