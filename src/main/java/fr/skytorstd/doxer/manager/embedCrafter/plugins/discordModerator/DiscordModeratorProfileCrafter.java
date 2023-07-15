package fr.skytorstd.doxer.manager.embedCrafter.plugins.discordModerator;

import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessages;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;

public class DiscordModeratorProfileCrafter extends DiscordModeratorCrafter{

    public static EmbedBuilder craftDiscordModeratorProfileBase() {
        EmbedBuilder embed = craftDiscordModeratorBase();

        return embed;
    }

    public static EmbedBuilder craftProfileEmbed(Member member, int warnCount) {
        EmbedBuilder embed = craftDiscordModeratorProfileBase();

        embed
                .setTitle(
                        String.format(
                                DiscordModeratorMessages.PROFILE_TITLE.getMessage(),
                                IconMessages.PROFILE_ICON.getIcon(),
                                member.getEffectiveName()
                                )
                )
                .setThumbnail(
                        member.getAvatarUrl()
                )
                .setDescription(
                        String.format(
                                DiscordModeratorMessages.PROFILE_DESCRIPTION.getMessage(),
                                member.getAsMention()
                        )
                )
                .addField(
                        DiscordModeratorMessages.PROFILE_IDENTIFIANT.getMessage(),
                        member.getId(),
                        false
                )
                .addField(
                        DiscordModeratorMessages.PROFILE_WARNS.getMessage(),
                        String.valueOf(warnCount),
                        false
                )
                .addField(
                        DiscordModeratorMessages.PROFILE_STATUS.getMessage(),
                        member.getOnlineStatus().toString(),
                        false
                )
                .addField(
                        DiscordModeratorMessages.PROFILE_CREATED_AT.getMessage(),
                        member.getTimeCreated().toString(),
                        false
                );

        return embed;
    }

}
