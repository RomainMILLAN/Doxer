package fr.skytorstd.doxer.manager.embedCrafter;

import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.application.SystemMessages;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class ErrorCrafter extends EmbedCrafterBase {
    private static Color colorError = Color.RED;

    private static EmbedBuilder craftErrorEmbedBase(String pluginName) {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(colorError)
                .setTitle(IconMessages.ERROR.getIcon() + " " + pluginName);

        return embed;
    }

    public static EmbedBuilder craftErrorDescriptionEmbed(String pluginName, String description) {
        EmbedBuilder embed = craftErrorEmbedBase(pluginName);

        embed.setDescription(description);

        return embed;
    }

    public static EmbedBuilder craftErrorCommand(String pluginName, String commandError) {
        EmbedBuilder embed = craftErrorEmbedBase(pluginName);

        embed.setDescription("> " + IconMessages.ERROR.getIcon() + " " + SystemMessages.INCORRECT_COMMAND.getMessage() + " (`" + commandError + "`)");

        return embed;
    }
}
