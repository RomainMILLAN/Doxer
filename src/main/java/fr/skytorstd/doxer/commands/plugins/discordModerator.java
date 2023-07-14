package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessage;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;

public class discordModerator extends pluginSlashInterface {

    public discordModerator() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
            new Plugin(
                    DiscordModeratorMessage.PLUGIN_NAME.getMessage(),
                    DiscordModeratorMessage.PLUGIN_DESCRIPTION.getMessage(),
                    new ArrayList<String>() {
                        {
                            add(DiscordModeratorMessage.PLUGIN_COMMAND_1.getMessage());
                            add(DiscordModeratorMessage.PLUGIN_COMMAND_2.getMessage());
                            add(DiscordModeratorMessage.PLUGIN_COMMAND_3.getMessage());
                        }
                    }
            )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

    }
}
