package fr.skytorstd.doxer.commands.plugins;

import fr.romainmillan.jdw.JavaDiscordWebhook;
import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.MemberPermission;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.MessageMoverCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.MemberPermissionStates;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.application.SystemMessages;
import fr.skytorstd.doxer.states.messages.plugin.MessageMoverMessages;
import fr.skytorstd.doxer.states.plugins.MessageMoverStates;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class messageMover extends pluginSlashInterface {

    public messageMover() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        MessageMoverStates.PLUGIN_NAME.getState(),
                        MessageMoverMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {
                            {
                                add(MessageMoverMessages.PLUGIN_COMMAND_MOVE.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equalsIgnoreCase(MessageMoverStates.PLUGIN_COMMAND_MOVE_PREFIX.getState())){

            if(!MemberPermission.getInstance().isStaffMember(event.getMember())) {
                event.replyEmbeds(
                        ErrorCrafter.craftErrorPermission(
                                MessageMoverStates.PLUGIN_NAME.getState(),
                                event.getMember(),
                                event.getCommandString(),
                                MemberPermissionStates.STAFF
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
                        MessageMoverStates.PLUGIN_NAME.getState(),
                        String.format(
                                SystemMessages.INCORRECT_PERMISSION_WITH_PERMISSION.getMessage(),
                                MemberPermissionStates.STAFF.getMessage()
                        ),
                        event.getCommandString(),
                        LogState.ERROR,
                        event.getMember(),
                        event.getGuild()
                );
            }

            moveMessage(event);
        }
    }

    private void moveMessage(SlashCommandInteractionEvent event) {
        String messageId = event.getOption(
                MessageMoverStates.PLUGIN_OPTION_MOVE_MESSAGE_ID_NAME.getState()
        ).getAsString();
        String channelId = event.getOption(
                MessageMoverStates.PLUGIN_OPTION_MOVE_CHANNEL_ID_NAME.getState()
        ).getAsString();

        TextChannel newChannel = event.getGuild().getTextChannelById(channelId);
        if(null == newChannel) {
            event.replyEmbeds(
                    ErrorCrafter.craftErrorDescriptionEmbed(
                            MessageMoverStates.PLUGIN_NAME.getState(),
                            String.format(
                                    MessageMoverMessages.ERROR_CHANNEL_NOT_FOUND.getMessage(),
                                    channelId
                            )
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
                    MessageMoverStates.PLUGIN_NAME.getState(),
                    String.format(
                            MessageMoverMessages.SENTRY_CHANNEL_NOT_FOUND.getMessage(),
                            channelId
                    ),
                    event.getCommandString(),
                    LogState.ERROR,
                    event.getMember(),
                    event.getGuild()
            );

            return;
        }

        event.getChannel().retrieveMessageById(messageId).queue(message -> {

            newChannel.createWebhook(message.getAuthor().getName()).queue(webhook -> {
                JavaDiscordWebhook javaDiscordWebhook = new JavaDiscordWebhook(webhook.getToken(), webhook.getIdLong());

                javaDiscordWebhook.setContent(message.getContentRaw());
                javaDiscordWebhook.setUsername(message.getAuthor().getName());
                javaDiscordWebhook.setAvatarUrl(message.getAuthor().getAvatarUrl());

                try {
                    javaDiscordWebhook.execute();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            event.replyEmbeds(
                    MessageMoverCrafter.craftMoveSuccessEmbed(
                            newChannel,
                            message.getMember()
                    ).build()
            )
                    .setEphemeral(true)
                    .queue(reply -> {
                        reply.deleteOriginal().queueAfter(
                                QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
            Sentry.getInstance().toLog(
                    MessageMoverStates.PLUGIN_NAME.getState(),
                    String.format(
                            MessageMoverMessages.SENTRY_MESSAGE_MOVE.getMessage(),
                            message.getContentRaw(),
                            newChannel.getAsMention()
                    ),
                    event.getCommandString(),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
            message.delete().queue();
        });
    }
}
