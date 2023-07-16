package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.plugins.VoiceClick;
import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import fr.skytorstd.doxer.states.messages.plugin.VoiceClickMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.Channel;

import java.awt.*;
import java.util.ArrayList;

public class VoiceClickCrafter extends EmbedCrafterBase {
    private static EmbedBuilder craftVoiceClickBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed
                .setTitle(IconMessages.WRENCH.getIcon() + " VoiceClick")
                .setColor(Color.ORANGE);

        return embed;
    }

    public static EmbedBuilder craftVoiceClicksList(ArrayList<VoiceClick> voiceClicks) {
        EmbedBuilder embed = craftVoiceClickBase();

        StringBuilder description = new StringBuilder();
        for(VoiceClick vc : voiceClicks){
            description
                    .append("`")
                    .append(vc.getVc_id())
                    .append("` ▪ ")
                    .append(vc.getVc_name())
                    .append(" (`")
                    .append(vc.getVc_channel_id())
                    .append("`)\n");
        }

        if(voiceClicks.size() == 0) {
            description.append("*" + VoiceClickMessages.NO_VOICE_CLICK_CHANNELS.getMessage() + "*");
        }

        embed
                .setDescription(description.toString());

        return embed;
    }

    public static EmbedBuilder craftSuccessAddVoiceClick(Channel channel) {
        EmbedBuilder embed = craftVoiceClickBase();

        embed
                .setDescription(
                        String.format(
                                VoiceClickMessages.VOICE_CLICK_ADD.getMessage(),
                                channel.getAsMention()
                        )
                );

        return embed;
    }

    public static EmbedBuilder craftSuccessRemoveVoiceClick(VoiceClick vc) {
        EmbedBuilder embed = craftVoiceClickBase();

        embed
                .setDescription(
                        String.format(
                                VoiceClickMessages.VOICE_CLICK_REMOVE.getMessage(),
                                vc.getVc_channel_id()
                        )
                );

        return embed;
    }

}
