package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.MemberPermission;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.DiscordSecurityCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.MemberPermissionStates;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.application.SystemMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordSecurityMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
            securityCommand(event);
        }

        if(event.getName().equalsIgnoreCase(DiscordSecurityStates.PLUGIN_COMMAND_CONFIRM_PREFIX.getState())) {
            confirmCommand(event);
        }

    }

    private void securityCommand(SlashCommandInteractionEvent event) {
        if(!MemberPermission.getInstance().isOpMember(event.getMember())){
            event.replyEmbeds(
                    ErrorCrafter.craftErrorPermission(
                            DiscordSecurityStates.PLUGIN_NAME.getState(),
                            event.getMember(),
                            event.getCommandString(),
                            MemberPermissionStates.OP
                    ).build()
            )
                    .setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.ERROR_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });

            Sentry.getInstance().toLog(
                    DiscordSecurityStates.PLUGIN_NAME.getState(),
                    String.format(
                            SystemMessages.INCORRECT_PERMISSION_WITH_PERMISSION.getMessage(),
                            MemberPermissionStates.OP.getMessage()
                    ),
                    event.getCommandString(),
                    LogState.ERROR,
                    event.getMember(),
                    event.getGuild()
            );

            return;
        }

        boolean securityState = App.getConfiguration("ST_SECURITY").equals("TRUE");

        event.replyEmbeds(
                        DiscordSecurityCrafter.craftSecurityStateEmbed(securityState).build()
                )
                .setEphemeral(true)
                .queue(message -> {
                    message.deleteOriginal().queueAfter(
                            QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                            TimeUnit.SECONDS
                    );
                });

    }

    private void confirmCommand(SlashCommandInteractionEvent event) {

    }
}
