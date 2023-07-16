package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.GrouperCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.plugin.GrouperMessages;
import fr.skytorstd.doxer.states.plugins.GrouperStates;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class grouper extends pluginSlashInterface {
    public grouper() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        GrouperStates.PLUGIN_NAME.getState(),
                        GrouperMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {
                            {
                                add(GrouperMessages.PLUGIN_COMMAND_GROUPER.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equals(GrouperStates.PLUGIN_COMMAND_GROUPER_PREFIX.getState())){
            String groupName = event.getOption(
                    GrouperStates.PLUGIN_OPTION_GROUPER_NAME_NAME.getState()
            ).getAsString();

            event.getGuild().getCategoryById(
                    App.getConfiguration("C_GROUPER")
            ).createTextChannel(groupName)
                    .queue(textChannelGroup -> {
                        EnumSet<Permission> allow = EnumSet.of(Permission.MANAGE_CHANNEL, Permission.ADMINISTRATOR, Permission.MANAGE_PERMISSIONS, Permission.MANAGE_ROLES, Permission.MESSAGE_MANAGE, Permission.VIEW_CHANNEL, Permission.MESSAGE_HISTORY, Permission.MESSAGE_ADD_REACTION, Permission.MESSAGE_SEND, Permission.MESSAGE_ATTACH_FILES);
                        textChannelGroup.getManager().putPermissionOverride(event.getMember(), allow, null).queue();
                    });

            event.replyEmbeds(
                    GrouperCrafter.craftSuccessCreateGroup(groupName).build()
            )
                    .setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
            Sentry.getInstance().toLog(
                    GrouperStates.PLUGIN_NAME.getState(),
                    String.format(
                            GrouperMessages.GROUP_CREATED.getMessage(),
                            groupName
                    ),
                    event.getCommandString(),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

    }
}
