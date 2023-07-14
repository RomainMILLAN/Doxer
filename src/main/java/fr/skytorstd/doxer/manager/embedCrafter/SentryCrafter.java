package fr.skytorstd.doxer.manager.embedCrafter;

import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.messages.IconMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class SentryCrafter extends EmbedCrafterBase {
    private static Color colorSentry = Color.ORANGE;

    public static EmbedBuilder craftSentryEmbed(String title, String description, LogState logState, Member member) {
        EmbedBuilder embed = craftEmbedBase();

        embed.setTitle(IconMessages.DOCUMENT_WRITING.getIcon() + "**DISCORD SENTRY/" + title + "**")
                .setColor(colorSentry)
                .setDescription(title + " - " + member.getAsMention() + "\n > " + logState.getEmojiMessage() + " " + description);

        return embed;
    }

    public static EmbedBuilder craftSentryEmbedCommand(String title, String description, String command, LogState logState, Member member) {
        EmbedBuilder embed = craftSentryEmbed(title, description, logState, member);

        embed.addBlankField(false)
                .addField("Commande", "`" + command + "`", false);

        return embed;
    }

}
