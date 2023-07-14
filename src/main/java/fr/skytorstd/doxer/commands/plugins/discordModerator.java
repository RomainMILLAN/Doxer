package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.MemberPermission;
import fr.skytorstd.doxer.manager.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.application.MemberPermissionMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessage;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
                            add(DiscordModeratorMessage.PLUGIN_COMMAND_WARN_NAME.getMessage());
                            add(DiscordModeratorMessage.PLUGIN_COMMAND_2.getMessage());
                            add(DiscordModeratorMessage.PLUGIN_COMMAND_3.getMessage());
                        }
                    }
            )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(!MemberPermission.getInstance().isStaffMember(Objects.requireNonNull(event.getMember()))){
            event.replyEmbeds(
                    ErrorCrafter.craftErrorPermission(
                            DiscordModeratorMessage.PLUGIN_NAME.getMessage(),
                            event.getMember(),
                            event.getCommandString(),
                            MemberPermissionMessages.STAFF
                    ).build()
            ).queue(message -> {
                message.deleteOriginal().queueAfter(QueueAfterTimes.ERROR_TIME.getQueueAfterTime(), TimeUnit.SECONDS);
            });

            return;
        }

        if(event.getName().equalsIgnoreCase(DiscordModeratorMessage.PLUGIN_CHOICE_WARN_SHOW_NAME.getMessage())){
            warnListCommand(event);
        }

        if(event.getName().equalsIgnoreCase(DiscordModeratorMessage.PLUGIN_CHOICE_WARN_ADD_NAME.getMessage())){
            addWarnCommand(event);
        }

        if(event.getName().equalsIgnoreCase(DiscordModeratorMessage.PLUGIN_CHOICE_WARN_REMOVE_NAME.getMessage())){
            removeWarnCommand(event);
        }
    }

    private void warnListCommand(SlashCommandInteractionEvent event) {

    }

    private void addWarnCommand(SlashCommandInteractionEvent event) {

    }

    private void removeWarnCommand(SlashCommandInteractionEvent event) {

    }
}
