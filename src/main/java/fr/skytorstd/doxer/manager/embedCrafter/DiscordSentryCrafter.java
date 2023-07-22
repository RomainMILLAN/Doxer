package fr.skytorstd.doxer.manager.embedCrafter;

import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.messages.IconMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class DiscordSentryCrafter extends EmbedCrafterBase {
    private static Color colorSentry = Color.WHITE;

    public static EmbedBuilder craftDiscordSentryEmbed(String title, String description, Member member) {
        EmbedBuilder embed = craftEmbedBase();

        embed.setTitle(IconMessages.DOCUMENT_WRITING.getIcon() + "**DISCORD SENTRY/" + title + "**")
                .setColor(colorSentry)
                .setDescription(title + " - " + member.getAsMention() + "\n > " + description);

        return embed;
    }

}
