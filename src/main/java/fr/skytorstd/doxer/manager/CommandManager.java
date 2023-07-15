package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordSecurityMessages;
import fr.skytorstd.doxer.states.plugins.DiscordModeratorStates;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;
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
        commandData.add(getDiscordModeratorWarnCommand());
        commandData.add(getDiscordModeratorProfileCommand());
        commandData.add(getDiscordSecuritySecurityCommand());
        commandData.add(getDiscordSecurityConfirmCommand());

        return commandData;
    }

    private static SlashCommandData getHelperCommand() {
        OptionData plugin = new OptionData(
                OptionType.STRING,
                HelperMessage.COMMAND_1_OPTION_1_PLUGIN_NAME.getMessage(),
                HelperMessage.COMMAND_1_OPTION_1_PLUGIN_DESCRIPTION.getMessage()
        )
                .setRequired(true);

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

    private static SlashCommandData getDiscordModeratorWarnCommand() {
        OptionData user = new OptionData(
                OptionType.MENTIONABLE,
                DiscordModeratorStates.PLUGIN_OPTION_WARN_USER_NAME.getState(),
                DiscordModeratorMessages.PLUGIN_OPTION_WARN_USER_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        OptionData action = new OptionData(
                OptionType.STRING,
                DiscordModeratorStates.PLUGIN_OPTION_WARN_ACTION_NAME.getState(),
                DiscordModeratorMessages.PLUGIN_OPTION_WARN_ACTION_DESCRIPTION.getMessage()
        )
                .addChoice(
                        DiscordModeratorMessages.PLUGIN_CHOICE_WARN_SHOW_DESCRIPTION.getMessage(),
                        DiscordModeratorStates.PLUGIN_CHOICE_WARN_SHOW_NAME.getState()
                )
                .addChoice(
                        DiscordModeratorMessages.PLUGIN_CHOICE_WARN_ADD_DESCRIPTION.getMessage(),
                        DiscordModeratorStates.PLUGIN_CHOICE_WARN_ADD_NAME.getState()
                        )
                .addChoice(
                        DiscordModeratorMessages.PLUGIN_CHOICE_WARN_REMOVE_DESCRIPTION.getMessage(),
                        DiscordModeratorStates.PLUGIN_CHOICE_WARN_REMOVE_NAME.getState()
                        )
                .setRequired(true);

        return Commands.slash(
                DiscordModeratorStates.PLUGIN_COMMAND_WARN_PREFIX.getState(),
            DiscordModeratorMessages.PLUGIN_COMMAND_WARN_DESCRIPTION.getMessage()
        ).addOptions(user, action);
    }

    private static SlashCommandData getDiscordModeratorProfileCommand() {
        OptionData user = new OptionData(
                OptionType.MENTIONABLE,
                DiscordModeratorStates.PLUGIN_OPTION_PROFILE_USER_NAME.getState(),
                DiscordModeratorMessages.PLUGIN_OPTION_PROFILE_USER_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        return Commands.slash(
                DiscordModeratorStates.PLUGIN_COMMAND_PROFILE_PREFIX.getState(),
                DiscordModeratorMessages.PLUGIN_COMMAND_PROFILE_DESCRIPTION.getMessage()
        ).addOptions(user);
    }

    private static SlashCommandData getDiscordSecuritySecurityCommand() {
        OptionData defaultGroup = new OptionData(
                OptionType.STRING,
                DiscordSecurityStates.PLUGIN_OPTION_SECURITY_DEFAULT_GROUP_NAME.getState(),
                DiscordSecurityMessages.PLUGIN_OPTION_SECURITY_DEFAULT_GROUP_DESCRIPTION.getMessage()
        )
                .setRequired(false);

        return Commands.slash(
                DiscordSecurityStates.PLUGIN_COMMAND_SECURITY_PREFIX.getState(),
                DiscordSecurityMessages.PLUGIN_COMMAND_SECURITY_DESCRIPTION.getMessage()
        ).addOptions(defaultGroup);
    }

    private static SlashCommandData getDiscordSecurityConfirmCommand() {
        OptionData user = new OptionData(
                OptionType.MENTIONABLE,
                DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_USER_NAME.getState(),
                DiscordSecurityMessages.PLUGIN_OPTION_CONFIRM_USER_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        OptionData group = new OptionData(
                OptionType.MENTIONABLE,
                DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_GROUP_NAME.getState(),
                DiscordSecurityMessages.PLUGIN_OPTION_CONFIRM_GROUP_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        return Commands.slash(
                DiscordSecurityStates.PLUGIN_COMMAND_CONFIRM_PREFIX.getState(),
                DiscordSecurityMessages.PLUGIN_COMMAND_CONFIRM_DESCRIPTION.getMessage()
        ).addOptions(user, group);
    }
}
