package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.plugin.PollExclamerMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class PollExclamerCrafter extends EmbedCrafterBase {

    private static EmbedBuilder craftPollExclamerBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(Color.CYAN);

        return embed;
    }

    public static EmbedBuilder craftPollEmbed(String question, Member member) {
        EmbedBuilder embed = craftPollExclamerBase();

        embed.setTitle(
                IconMessages.DOCUMENT_WRITING.getIcon()
                + " " + PollExclamerMessages.POLL_OF.getMessage() + " " + member.getEffectiveName()
        )
                .setDescription(question);

        return embed;
    }

    public static EmbedBuilder craftSuccessPollCreatedEmbed() {
        EmbedBuilder embed = craftEmbedBase();

        embed
                .setColor(Color.GREEN)
                .setDescription(
                        IconMessages.SUCCESS.getIcon()
                        + " " + PollExclamerMessages.POLL_CREATED.getMessage()
                );

        return embed;
    }
}
