package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.messages.application.BotMessages;
import fr.skytorstd.doxer.states.messages.application.UploadMessages;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class DiscordWebhookApi {

    public static void sendConnectedNotification() {
        StringBuilder body = new StringBuilder()
                .append("{\"embeds\":[{\"title\":\"📊 Service connexion\",\"color\":65280,\"fields\":[{\"name\":\"Service name\",\"value\":\"")
                .append(BotMessages.BOT_NAME.getMessage())
                .append("\"},{\"name\":\"State\",\"value\":\"")
                .append(UploadMessages.NOTIFICATION_CONNECTED.getMessage())
                .append("\"}]}]}");

        sendDiscordWebhookEmbed(body);
    }

    public static void sendDisconnectedNotification() {
        StringBuilder body = new StringBuilder()
                .append("{\"embeds\":[{\"title\":\"📊 Service connexion\",\"color\":16711680,\"fields\":[{\"name\":\"Service name\",\"value\":\"")
                .append(BotMessages.BOT_NAME.getMessage())
                .append("\"},{\"name\":\"State\",\"value\":\"")
                .append(UploadMessages.NOTIFICATION_DISCONNECTED.getMessage())
                .append("\"}]}]}");

        sendDiscordWebhookEmbed(body);
    }

    private static void sendDiscordWebhookEmbed(StringBuilder body){
        if(App.getConfiguration("DISCORD_WEBHOOK_URL").equals("")){
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
            URI.create(App.getConfiguration("DISCORD_WEBHOOK_URL").toString())
        )
        .POST(BodyPublishers.ofString(body.toString()))
        .header("Content-Type", "application/json")
        .build();

        ConsoleManager.getInstance().toConsole(
            body.toString(),
            ConsoleState.DEBUG
        );
        ConsoleManager.getInstance().toConsole(
            App.getConfiguration("DISCORD_WEBHOOK_URL"),
            ConsoleState.DEBUG
        );

        try {
            client.send(request, BodyHandlers.discarding());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
