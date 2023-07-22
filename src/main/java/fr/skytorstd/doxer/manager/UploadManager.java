package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.embedCrafter.UploadCrafter;
import fr.skytorstd.doxer.states.messages.application.UploadMessages;

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

        SignalApi.sendSignalPersonnalMessage(
                String.format(
                        UploadMessages.SIGNAL_CONNECTED.getMessage(),
                        App.getJda().getSelfUser().getName()
                )
        );
    }
    public static void sendDisconnectmessage() {
        if(!App.getConfiguration("APP_ENV").equals("PROD") && !App.getConfiguration("APP_ENV").equals("STAGING")){
            return;
        }

        SignalApi.sendSignalPersonnalMessage(
                String.format(
                        UploadMessages.SIGNAL_DISCONNECTED.getMessage(),
                        App.getJda().getSelfUser().getName()
                )
        );
    }

}
