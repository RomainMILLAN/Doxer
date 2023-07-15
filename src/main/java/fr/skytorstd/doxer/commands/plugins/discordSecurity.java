package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.messages.plugin.DiscordSecurityMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;

public class discordSecurity extends pluginSlashInterface {

    public discordSecurity() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        DiscordSecurityStates.PLUGIN_NAME.getState(),
                        DiscordSecurityMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {
                            {
                                add(DiscordSecurityMessages.PLUGIN_COMMAND_SECURITY.getMessage());
                                add(DiscordSecurityMessages.PLUGIN_COMMAND_CONFIRM.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equalsIgnoreCase(DiscordSecurityStates.PLUGIN_COMMAND_SECURITY_PREFIX.getState())) {

        }

    }
}
