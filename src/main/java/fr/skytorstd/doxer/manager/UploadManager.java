package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.embedCrafter.UploadCrafter;

public class UploadManager {

    public static void sendConnectedMessage() {
        if(!App.getConfiguration("APP_ENV").equals("PROD") && !App.getConfiguration("APP_ENV").equals("STAGING")){
            return;
        }

        App.getJda().getGuildById(App.getConfiguration("GUILD_ID"))
                .getTextChannelById(
                        App.getConfiguration("TC_SENTRY")
                )
                .sendMessageEmbeds(
                        UploadCrafter.craftConnectEmbed(App.getJda().getSelfUser())
                                .build()
                )
                .queue();

        DiscordWebhookApi.sendConnectedNotification();;
    }
    public static void sendDisconnectmessage() {
        if(!App.getConfiguration("APP_ENV").equals("PROD") && !App.getConfiguration("APP_ENV").equals("STAGING")){
            return;
        }

        DiscordWebhookApi.sendDisconnectedNotification();
    }

}
