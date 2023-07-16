package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import fr.skytorstd.doxer.states.messages.plugin.MessageMoverMessages;
import fr.skytorstd.doxer.states.plugins.MessageMoverStates;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.ArrayList;

public class MessageMoverCrafter extends EmbedCrafterBase {
    private static EmbedBuilder craftMessageMoverBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(Color.ORANGE)
                .setTitle(
                        IconMessages.DOCUMENT_WRITING.getIcon() + " **" + MessageMoverStates.PLUGIN_NAME.getState() + "**"
                );

        return embed;
    }

    public static EmbedBuilder craftMoveSuccessEmbed(TextChannel newChannel, Member member) {
        EmbedBuilder embed = craftMessageMoverBase();

        embed.setDescription(
                String.format(
                        MessageMoverMessages.MESSAGE_MOVE.getMessage(),
                        newChannel.getAsMention()
                )
        );

        return embed;
    }


}
