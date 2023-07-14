package fr.skytorstd.doxer.commands;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.HelperEmbedCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.commandSlashInterface;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.application.SystemMessages;
import fr.skytorstd.doxer.states.messages.command.HelperMessage;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.concurrent.TimeUnit;

public class helper extends commandSlashInterface {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equals(HelperMessage.COMMAND_PLUGINS_PREFIX.getMessage())){
            // /plugins
            event.replyEmbeds(
                    HelperEmbedCrafter
                            .craftPluginsList(App.getPlugins())
                            .build()
            ).queue();

            Sentry.getInstance().toLog(
                    HelperMessage.COMMAND_NAME.getMessage(),
                    HelperMessage.SENTRY_PLUGINS_COMMAND_ACTIVATE.getMessage(),
                    event.getCommandString(),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

        if(event.getName().equals(HelperMessage.COMMAND_HELPER_PREFIX.getMessage())){
            // /helper <plugin>
            if(null == event.getOption(HelperMessage.COMMAND_1_OPTION_1_PLUGIN_NAME.getMessage())) {
                event.replyEmbeds(
                        ErrorCrafter.craftErrorCommand(
                                HelperMessage.COMMAND_NAME.getMessage(),
                                event.getCommandString()
                        ).build()
                ).queue(message -> {
                    message.deleteOriginal().queueAfter(QueueAfterTimes.ERROR_TIME.getQueueAfterTime(), TimeUnit.SECONDS);
                });

                Sentry.getInstance().toLog(
                        HelperMessage.COMMAND_NAME.getMessage(),
                        SystemMessages.INCORRECT_COMMAND.getMessage(),
                        event.getCommandString(),
                        LogState.ERROR,
                        event.getMember(),
                        event.getGuild()
                );

                return;
            }

            String pluginNameToSearch = event.getOption(
                    HelperMessage.COMMAND_1_OPTION_1_PLUGIN_NAME.getMessage()
            ).getAsString();

            Plugin plugin = App.getPluginByName(
                    pluginNameToSearch
            );

            if(plugin == null) {
                event.replyEmbeds(
                        ErrorCrafter.craftErrorDescriptionEmbed(
                                HelperMessage.COMMAND_NAME.getMessage(),
                                HelperMessage.NO_PLUGIN_FOUND.getMessage()
                        ).build()
                ).queue();

                Sentry.getInstance().toLog(
                        HelperMessage.COMMAND_NAME.getMessage(),
                        HelperMessage.SENTRY_PLUGIN_COMMAND_ACTIVATE_PLUGIN_NOT_FOUND.getMessage() + " (`" + pluginNameToSearch + "`)",
                        event.getCommandString(),
                        LogState.SUCCESSFUL,
                        event.getMember(),
                        event.getGuild()
                );
            }

            event.replyEmbeds(
                    HelperEmbedCrafter
                            .craftHelperEmbed(plugin)
                            .build()
            ).queue();

            Sentry.getInstance().toLog(
                    HelperMessage.COMMAND_NAME.getMessage(),
                    HelperMessage.SENTRY_PLUGIN_COMMAND_ACTIVATE.getMessage() + " (`" + plugin.getName() + "`)",
                    event.getCommandString(),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

    }

}
