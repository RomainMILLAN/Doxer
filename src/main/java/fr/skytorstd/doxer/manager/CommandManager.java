package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import fr.skytorstd.doxer.states.messages.plugin.*;
import fr.skytorstd.doxer.states.plugins.*;
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
        commandData.add(getPollExclamerPollCommand());
        commandData.add(getMessageMoverMoveCommand());
        commandData.add(getWeatherWeatherCommand());

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
        return Commands.slash(
                DiscordSecurityStates.PLUGIN_COMMAND_SECURITY_PREFIX.getState(),
                DiscordSecurityMessages.PLUGIN_COMMAND_SECURITY_DESCRIPTION.getMessage()
        );
    }

    private static SlashCommandData getDiscordSecurityConfirmCommand() {
        OptionData user = new OptionData(
                OptionType.USER,
                DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_USER_NAME.getState(),
                DiscordSecurityMessages.PLUGIN_OPTION_CONFIRM_USER_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        OptionData group = new OptionData(
                OptionType.ROLE,
                DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_GROUP_NAME.getState(),
                DiscordSecurityMessages.PLUGIN_OPTION_CONFIRM_GROUP_DESCRIPTION.getMessage()
        )
                .setRequired(false);

        OptionData nickname = new OptionData(
                OptionType.STRING,
                DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_NICKNAME_NAME.getState(),
                DiscordSecurityMessages.PLUGIN_OPTION_CONFIRM_NICKNAME_DESCRIPTION.getMessage()
        )
                .setRequired(false);

        return Commands.slash(
                DiscordSecurityStates.PLUGIN_COMMAND_CONFIRM_PREFIX.getState(),
                DiscordSecurityMessages.PLUGIN_COMMAND_CONFIRM_DESCRIPTION.getMessage()
        ).addOptions(user, group, nickname);
    }

    private static SlashCommandData getPollExclamerPollCommand() {
        OptionData question = new OptionData(
                OptionType.STRING,
                PollExclamerStates.PLUGIN_OPTION_POLL_QUESTION_NAME.getState(),
                PollExclamerMessages.PLUGIN_OPTION_QUESTION_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        OptionData reponse =  new OptionData(
                OptionType.STRING,
                PollExclamerStates.PLUGIN_OPTION_POLL_ANSWER_NAME.getState(),
                PollExclamerMessages.PLUGIN_OPTION_ANSWER_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        return Commands.slash(
                PollExclamerStates.PLUGIN_COMMAND_POLL_PREFIX.getState(),
                PollExclamerMessages.PLUGIN_COMMAND_POLL_DESCRIPTION.getMessage()
        ).addOptions(question, reponse);
    }

    private static SlashCommandData getMessageMoverMoveCommand(){
        OptionData messageId = new OptionData(
                OptionType.STRING,
                MessageMoverStates.PLUGIN_OPTION_MOVE_MESSAGE_ID_NAME.getState(),
                MessageMoverMessages.PLUGIN_OPTION_MOVE_MESSAGE_ID_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        OptionData channelId = new OptionData(
                OptionType.STRING,
                MessageMoverStates.PLUGIN_OPTION_MOVE_CHANNEL_ID_NAME.getState(),
                MessageMoverMessages.PLUGIN_OPTION_MOVE_CHANNEL_ID_DESCRIPTION.getMessage()
        )
                .setRequired(true);

        return Commands.slash(
                MessageMoverStates.PLUGIN_COMMAND_MOVE_PREFIX.getState(),
                MessageMoverMessages.PLUGIN_COMMAND_MOVE_DESCRIPTION.getMessage()
        ).addOptions(messageId, channelId);
    }

    private static SlashCommandData getWeatherWeatherCommand() {
        OptionData ville = new OptionData(
                OptionType.STRING,
                WeatherStates.PLUGIN_OPTION_WEATHER_VILLE_NAME.getState(),
                WeatherMessages.PLUGIN_OPTION_WEATHER_VILLE_DESCRIPTION.getMessage()
        )
                .setRequired(false);

        return Commands.slash(
                WeatherStates.PLUGIN_COMMAND_WEATHER_PREFIX.getState(),
                WeatherMessages.PLUGIN_COMMAND_WEATHER_COMMAND.getMessage()
        ).addOptions(ville);
    }
}
