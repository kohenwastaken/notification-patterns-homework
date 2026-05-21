# Phase 2 AI Log

## Prompt

burada structural pattern olarak adapter kullandım. peki facade kullansam nasıl olurdu? daha mı iyi olurdu?
## AI Yanıtı

Evet, burada Adapter doğru bir seçim çünkü ExternalDiscordApi senin sistemindeki Notification arayüzüne uymuyor.

Senin sistemin şunu bekliyor:

void send(String receiver, String message);

Ama Discord API şunu istiyor:

sendDiscordMessage(String serverName, String channelName, String message);

Yani arayüzler uyumsuz. Bu yüzden DiscordNotificationAdapter, Discord API’yi Notification gibi davranacak hale getiriyor.

Facade kullansaydın nasıl olurdu?

Facade daha çok karmaşık bir alt sistemi basitleştirmek için kullanılırdı. Örneğin Discord tarafında sadece tek bir API çağrısı değil de şöyle bir süreç olsaydı:

connectToServer();
authenticateBot();
findChannel();
sendMessage();
disconnect();

O zaman DiscordFacade mantıklı olurdu.

Örnek:

public class DiscordFacade {

    private ExternalDiscordApi externalDiscordApi;
    private String serverName;

    public DiscordFacade(ExternalDiscordApi externalDiscordApi, String serverName) {
        this.externalDiscordApi = externalDiscordApi;
        this.serverName = serverName;
    }

    public void sendMessageToChannel(String channelName, String message) {
        externalDiscordApi.connect();
        externalDiscordApi.authenticate();
        externalDiscordApi.sendDiscordMessage(serverName, channelName, message);
        externalDiscordApi.disconnect();
    }
}

Sonra bunu Notification ile kullanmak istersen yine bir adapter gerekebilir:

public class DiscordNotificationAdapter implements Notification {

    private DiscordFacade discordFacade;

    public DiscordNotificationAdapter(DiscordFacade discordFacade) {
        this.discordFacade = discordFacade;
    }

    @Override
    public void send(String receiver, String message) {
        discordFacade.sendMessageToChannel(receiver, message);
    }
}

Factory de şöyle olurdu:

if (type.equalsIgnoreCase("DISCORD")) {
DiscordFacade facade = new DiscordFacade(
new ExternalDiscordApi(),
"Software Engineering Server"
);

    return new DiscordNotificationAdapter(facade);
}
Hangisi daha iyi?

Bu örnekte Adapter daha iyi ve daha yerinde.

Çünkü senin problemin şu:

Discord bildirim sistemini mevcut Notification interface’ine uydurmak.

Bu tam olarak Adapter Pattern problemidir.

Facade ise şu durumda daha iyi olurdu:

Discord API karmaşık, çok adımlı, birçok sınıfla çalışan bir sistem ve sen bunu basit bir metodun arkasına saklamak istiyorsun.

Yani:

Notification

arayüzüne uydurmak istiyorsan: Adapter

Karmaşık Discord işlemlerini basitleştirmek istiyorsan: Facade

İkisi beraber de kullanılabilir:

NotificationFactory
|
v
DiscordNotificationAdapter
|
v
DiscordFacade
|
v
ExternalDiscordApi

Bu tasarımda:

Adapter, Discord’u Notification sistemine uydurur.
Facade, Discord API’nin karmaşıklığını saklar.

Senin mevcut kodun için sonuç: Adapter kullanman doğru. Facade tek başına burada Adapter’ın yerini tam olarak tutmaz.
## Benim Değerlendirmem

Bu durumda Adapter Pattern daha uygundu çünkü temel problem karmaşık bir alt sistemi basitleştirmek değil, uyumsuz bir arayüzü mevcut Notification arayüzüne dönüştürmekti.

## AI'ın Eksik veya Yanlış Kalan Tarafı

AI da adapter patternin daha iyi seçenek olduğunu söyledi. Bu konuda hemfikiriz. Ama arkadaş facade nasıl kullanılacağını da gereksiz yere anlatmış. 
## Uygulanan Çözüm

ExternalDiscordApi sınıfı oluşturuldu. Bu sınıf mevcut Notification interface'ine uymadığı için DiscordNotificationAdapter sınıfı yazıldı. Ayrıca bildirimlere ek davranış kazandırmak için Decorator Pattern uygulandı. LoggingNotificationDecorator ve PriorityNotificationDecorator sınıfları oluşturuldu.