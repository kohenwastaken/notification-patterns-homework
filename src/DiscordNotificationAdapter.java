public class DiscordNotificationAdapter implements Notification {

    private ExternalDiscordApi externalDiscordApi;
    private String serverName;

    public DiscordNotificationAdapter(ExternalDiscordApi externalDiscordApi, String serverName) {
        this.externalDiscordApi = externalDiscordApi;
        this.serverName = serverName;
    }

    @Override
    public void send(String receiver, String message) {
        externalDiscordApi.sendDiscordMessage(serverName, receiver, message);
    }
}