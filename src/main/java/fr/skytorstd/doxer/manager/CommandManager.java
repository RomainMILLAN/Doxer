package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
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

}
