package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.ArrayList;

public class HelperEmbedCrafter extends EmbedCrafterBase {
    private static EmbedBuilder craftHelperBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(Color.PINK);

        return embed;
    }

    public static EmbedBuilder craftPluginsList(ArrayList<Plugin> pluginList) {
        EmbedBuilder embed = craftHelperBase();

        StringBuilder description = new StringBuilder();
        for(Plugin plugin : pluginList){
            description.append(" ▪ " + plugin.getName() + " \n");
        }

        if(pluginList.size() == 0) {
            description.append("*" + HelperMessage.NO_PLUGIN_ACTIVATED.getMessage() + "*");
        }

        embed.setTitle(IconMessages.WRENCH.getIcon() + " Plugins")
                .setDescription(description.toString());

        return embed;
    }

    public static EmbedBuilder craftHelperEmbed(Plugin plugin) {
        EmbedBuilder embed = craftHelperBase();

        StringBuilder description = new StringBuilder();
        description.append("> " + plugin.getDescription() + "\n\n");
        for(String pluginCommand : plugin.getCommands()) {
            description.append(" ▪ " + pluginCommand + " \n");
        }

        embed.setTitle(IconMessages.DOCUMENT.getIcon() + " " + plugin.getName())
                .setDescription(description.toString());

        return embed;
    }

}
