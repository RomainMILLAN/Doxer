package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessage;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    public static List<CommandData> updateSlashCommands(){
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(getHelperCommand());
        commandData.add(getPluginsListCommand());
        commandData.add(getWarnCommand());

        return commandData;
    }

    private static SlashCommandData getHelperCommand() {
        OptionData plugin = new OptionData(
                OptionType.STRING,
                HelperMessage.COMMAND_1_OPTION_1_PLUGIN_NAME.getMessage(),
                HelperMessage.COMMAND_1_OPTION_1_PLUGIN_DESCRIPTION.getMessage()
        );

        for(Plugin pl : App.getPlugins()){
            plugin.addChoice(pl.getName(), pl.getName());
        }

        return Commands.slash(
                HelperMessage.COMMAND_HELPER_PREFIX.getMessage(),
                HelperMessage.COMMAND_DESCRIPTION.getMessage()
        ).addOptions(plugin);
    }

    private static SlashCommandData getPluginsListCommand() {
        return Commands.slash(
                HelperMessage.COMMAND_PLUGINS_PREFIX.getMessage(),
                HelperMessage.COMMAND_DESCRIPTION.getMessage()
        );
    }

    private static SlashCommandData getWarnCommand() {
        OptionData user = new OptionData(
                OptionType.USER,
                DiscordModeratorMessage.PLUGIN_OPTION_WARN_USER_NAME.getMessage(),
                DiscordModeratorMessage.PLUGIN_OPTION_WARN_USER_DESCRIPTION.getMessage()
        );

        OptionData action = new OptionData(
                OptionType.STRING,
                DiscordModeratorMessage.PLUGIN_OPTION_WARN_ACTION_NAME.getMessage(),
                DiscordModeratorMessage.PLUGIN_OPTION_WARN_ACTION_DESCRIPTION.getMessage()
        )
                .addChoice(
                        DiscordModeratorMessage.PLUGIN_CHOICE_WARN_SHOW_NAME.getMessage(),
                        DiscordModeratorMessage.PLUGIN_CHOICE_WARN_SHOW_DESCRIPTION.getMessage()
                )
                .addChoice(
                        DiscordModeratorMessage.PLUGIN_CHOICE_WARN_ADD_NAME.getMessage(),
                        DiscordModeratorMessage.PLUGIN_CHOICE_WARN_ADD_DESCRIPTION.getMessage()
                )
                .addChoice(
                        DiscordModeratorMessage.PLUGIN_CHOICE_WARN_REMOVE_NAME.getMessage(),
                        DiscordModeratorMessage.PLUGIN_CHOICE_WARN_REMOVE_DESCRIPTION.getMessage()
                );

        return Commands.slash(
            DiscordModeratorMessage.PLUGIN_COMMAND_WARN_PREFIX.getMessage(),
            DiscordModeratorMessage.PLUGIN_COMMAND_WARN_DESCRIPTION.getMessage()
        ).addOptions(user, action);
    }
}
