package fr.skytorstd.doxer.manager.embedCrafter.plugins.discordModerator;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class DiscordModeratorCrafter extends EmbedCrafterBase {

    protected static EmbedBuilder craftDiscordModeratorBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(Color.YELLOW);

        return embed;
    }



}
