package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordSecurityMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class DiscordSecurityCrafter extends EmbedCrafterBase {

    private static EmbedBuilder craftDiscordSecurityBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(Color.BLUE);

        return embed;
    }

    public static EmbedBuilder craftSecurityStateEmbed(boolean securityState) {
        EmbedBuilder embed = craftDiscordSecurityBase();

        embed
                .setTitle(DiscordSecurityMessages.EMBED_TITLE.getMessage())
                .setDescription(
                    String.format(
                        DiscordSecurityMessages.SECURITY_STATE.getMessage(),
                        securityState
                    )
                );

        return embed;
    }

    public static EmbedBuilder craftConfirmEmbed(Member memberConfirmed, Member staff) {
        EmbedBuilder embed = craftDiscordSecurityBase();

        embed.setDescription(
                String.format(
                        DiscordSecurityMessages.CONFIRM_MESSAGE.getMessage(),
                        memberConfirmed.getAsMention(),
                        staff.getAsMention()
                )
        );

        return embed;
    }

}
