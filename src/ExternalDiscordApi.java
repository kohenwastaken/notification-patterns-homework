public class ExternalDiscordApi {

    public void sendDiscordMessage(String serverName, String channelName, String content) {
        System.out.println("Connecting to external Discord API...");
        System.out.println("Sending message to Discord server: " + serverName);
        System.out.println("Channel: " + channelName);
        System.out.println("Content: " + content);
    }
}