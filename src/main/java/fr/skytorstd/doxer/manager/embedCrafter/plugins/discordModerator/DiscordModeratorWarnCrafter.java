package fr.skytorstd.doxer.manager.embedCrafter.plugins.discordModerator;

import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

import java.awt.*;

public class DiscordModeratorWarnCrafter extends DiscordModeratorCrafter {

    private static EmbedBuilder craftDiscordModeratorWarnBase(Member member) {
        EmbedBuilder embed = craftDiscordModeratorBase();

        embed.setTitle(IconMessages.CADENA_LOCK.getIcon() + " **Warns** - ("+ member.getAsMention() +")");

        return embed;
    }

    public static EmbedBuilder craftWarnListEmbed(Member member, String description) {
        EmbedBuilder embed = craftDiscordModeratorWarnBase(member);

        embed.setDescription(description);

        return embed;
    }

    public static EmbedBuilder craftSuccessAddWarnEmbed(Member member, String name) {
        EmbedBuilder embed = craftDiscordModeratorWarnBase(member);

        embed.setDescription(
                        String.format(
                                DiscordModeratorMessages.WARN_ADD.getMessage(),
                                member.getAsMention(),
                                name
                        )
                )
                .setColor(Color.GREEN);

        return embed;
    }

    public static EmbedBuilder craftSuccessRemoveWarn(Member member, String warnName) {
        EmbedBuilder embed = craftDiscordModeratorWarnBase(member);

        embed.setDescription(
                        String.format(
                                DiscordModeratorMessages.WARN_REMOVE.getMessage(),
                                member.getAsMention(),
                                warnName
                        )
                )
                .setColor(Color.GREEN);

        return embed;
    }

}
