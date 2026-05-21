# Evrimleşen Sistem Ödevi

## Seçilen Konu: A - Bildirim Sistemi

Bu konuyu seçtim çünkü farklı bildirim türleri başlangıçta tek bir sınıf içinde yönetiliyordu. Email, SMS ve Push bildirimleri if-else bloklarıyla ayrıldığı için sistem yeni bildirim türleri eklendikçe zor genişletilir hale geliyordu. Bu yapı Factory Method, Adapter, Decorator, Strategy ve Observer gibi tasarım örüntülerini uygulamak için uygundur.

## Proje Ne Yapar?

Bu proje farklı kanallar üzerinden bildirim gönderen basit bir Java uygulamasıdır. Başlangıçta tüm bildirim türleri tek bir sınıfta if-else bloklarıyla yönetiliyordu. Fazlar ilerledikçe sistem tasarım örüntüleriyle daha genişletilebilir, okunabilir ve bakımı kolay hale getirildi.

Desteklenen bildirim türleri:

- Email
- SMS
- Push
- Discord

## Kullanılan Tasarım Örüntüleri

| Faz | Örüntü | Amaç |
|---|---|---|
| Phase 1 | Factory Method | Bildirim nesnelerinin oluşturulmasını merkezi hale getirmek |
| Phase 2 | Adapter | Dış Discord API yapısını mevcut Notification arayüzüne uydurmak |
| Phase 2 | Decorator | Bildirimlere loglama ve öncelik gibi ek davranışlar eklemek |
| Phase 3 | Strategy | Mesaj formatlama davranışını çalışma zamanında değiştirilebilir yapmak |
| Phase 3 | Observer | Bildirim gönderildikten sonra audit ve analytics işlemlerini tetiklemek |

## Mimari Diyagram

Detaylı UML diyagramları:

- `docs/diagrams/phase1/`
- `docs/diagrams/phase2/`
- `docs/diagrams/phase3/`

## Nasıl Çalıştırılır?

Projeyi derlemek için:

```bash
javac -d out src/*.java