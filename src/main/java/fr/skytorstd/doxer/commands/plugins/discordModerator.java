package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.ConsoleManager;
import fr.skytorstd.doxer.manager.DateHourFormatter;
import fr.skytorstd.doxer.manager.MemberPermission;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.database.plugins.discordmoderator.DiscordModeratorWarnsDatabase;
import fr.skytorstd.doxer.manager.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.discordModerator.DiscordModeratorProfileCrafter;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.discordModerator.DiscordModeratorWarnCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.objects.plugins.discordmoderator.Warn;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.application.MemberPermissionMessages;
import fr.skytorstd.doxer.states.messages.plugin.DiscordModeratorMessages;
import fr.skytorstd.doxer.states.plugins.DiscordModeratorStates;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

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
                    DiscordModeratorStates.PLUGIN_NAME.getState(),
                    DiscordModeratorMessages.PLUGIN_DESCRIPTION.getMessage(),
                    new ArrayList<String>() {
                        {
                            add(DiscordModeratorMessages.PLUGIN_COMMAND_WARN_NAME.getMessage());
                            add(DiscordModeratorMessages.PLUGIN_COMMAND_PROFILE_NAME.getMessage());
                            add(DiscordModeratorMessages.PLUGIN_COMMAND_2.getMessage());
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
                            DiscordModeratorStates.PLUGIN_NAME.getState(),
                            event.getMember(),
                            event.getCommandString(),
                            MemberPermissionMessages.STAFF
                    ).build()
            ).queue(message -> {
                message.deleteOriginal().queueAfter(QueueAfterTimes.ERROR_TIME.getQueueAfterTime(), TimeUnit.SECONDS);
            });

            return;
        }

        if(event.getName().equalsIgnoreCase(DiscordModeratorStates.PLUGIN_COMMAND_WARN_PREFIX.getState())){
            if(Objects.requireNonNull(event.getOption(DiscordModeratorStates.PLUGIN_OPTION_WARN_ACTION_NAME.getState())).getAsString().equalsIgnoreCase(DiscordModeratorStates.PLUGIN_CHOICE_WARN_SHOW_NAME.getState())){
                warnListCommand(event);
            }

            if(Objects.requireNonNull(event.getOption(DiscordModeratorStates.PLUGIN_OPTION_WARN_ACTION_NAME.getState())).getAsString().equalsIgnoreCase(DiscordModeratorStates.PLUGIN_CHOICE_WARN_ADD_NAME.getState())){
                addWarnCommand(event);
            }

            if(Objects.requireNonNull(event.getOption(DiscordModeratorStates.PLUGIN_OPTION_WARN_ACTION_NAME.getState())).getAsString().equalsIgnoreCase(DiscordModeratorStates.PLUGIN_CHOICE_WARN_REMOVE_NAME.getState())){
                removeWarnCommand(event);
            }
        }

        if(event.getName().equalsIgnoreCase(DiscordModeratorStates.PLUGIN_COMMAND_PROFILE_PREFIX.getState())){
            profileCommand(event);
        }

    }

    private void warnListCommand(SlashCommandInteractionEvent event) {
        ArrayList<Warn> warnsList = DiscordModeratorWarnsDatabase.memberWarns(Objects.requireNonNull(event.getMember()));

        StringBuilder description = new StringBuilder();
        if(warnsList.size() == 0) {
            description
                    .append("> ")
                    .append(DiscordModeratorMessages.WARN_NONE.getMessage());
        }else {
            description.append("Liste des warns: \n");

            for(Warn warn : warnsList) {
                description
                        .append(" ▪ [`")
                        .append(warn.getId())
                        .append("`] ")
                        .append(warn.getName())
                        .append(" (*")
                        .append(warn.getCreated_at())
                        .append("*)\n");
            }
        }

        event.replyEmbeds(
                DiscordModeratorWarnCrafter.craftWarnListEmbed(event.getMember(), description.toString()).build()
        ).queue();
    }

    private void addWarnCommand(SlashCommandInteractionEvent event) {
        TextInput warnName = TextInput.create(
                DiscordModeratorStates.PLUGIN_TEXT_INPUT_WARN_ADD_NAME_ID.getState(),
                DiscordModeratorMessages.PLUGIN_TEXT_INPUT_WARN_ADD_NAME_DESCRIPTION.getMessage(),
                TextInputStyle.SHORT
        )
                .setRequired(true)
                .build();

        TextInput userId = TextInput.create(
                DiscordModeratorStates.PLUGIN_TEXT_INPUT_WARN_ADD_USER_ID.getState(),
                DiscordModeratorMessages.PLUGIN_TEXT_INPUT_WARN_ADD_USERID_DESCRIPTION.getMessage(),
                TextInputStyle.SHORT
        )
                .setRequired(true)
                .setValue(
                        Objects.requireNonNull(event.getOption(
                                DiscordModeratorStates.PLUGIN_OPTION_WARN_USER_NAME.getState()
                        )).getAsString()
                )
                .build();

        Modal warnAddModal = Modal.create(
                DiscordModeratorStates.PLUGIN_MODAL_WARN_ADD_ID.getState(),
                DiscordModeratorMessages.PLUGIN_MODAL_WARN_ADD_DESCRIPTION.getMessage()
        )
                .addActionRow(warnName)
                .addActionRow(userId)
                .build();

        event.replyModal(warnAddModal).queue();
    }

    private void removeWarnCommand(SlashCommandInteractionEvent event) {

        if(!MemberPermission.getInstance().isOpMember(Objects.requireNonNull(event.getMember()))){
            event.replyEmbeds(
                    ErrorCrafter.craftErrorPermission(
                            DiscordModeratorStates.PLUGIN_NAME.getState(),
                            event.getMember(),
                            event.getCommandString(),
                            MemberPermissionMessages.OP
                    ).build()
            ).setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.ERROR_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
        }

        TextInput warnId = TextInput.create(
                DiscordModeratorStates.PLUGIN_TEXT_INPUT_WARN_REMOVE_WARN_ID.getState(),
                DiscordModeratorMessages.PLUGIN_TEXT_INPUT_WARN_REMOVE_WARN_ID_DESCRIPTION.getMessage(),
                TextInputStyle.SHORT
        ).build();

        Modal warnRemove = Modal.create(
                DiscordModeratorStates.PLUGIN_MODAL_WARN_REMOVE_ID.getState(),
                DiscordModeratorMessages.PLUGIN_MODAL_WARN_REMOVE_DESCRIPTION.getMessage()
        )
                .addActionRow(warnId)
                .build();

        event.replyModal(warnRemove).queue();
    }

    private void profileCommand(SlashCommandInteractionEvent event) {
        Member member = Objects.requireNonNull(event.getOption(DiscordModeratorStates.PLUGIN_OPTION_PROFILE_USER_NAME.getState())).getAsMember();
        int warnCount = DiscordModeratorWarnsDatabase.memberCountWarns(member);

        if(member == null){
            event.replyEmbeds(
                    ErrorCrafter.craftErrorDescriptionEmbed(
                            DiscordModeratorStates.PLUGIN_NAME.getState(),
                            DiscordModeratorMessages.PROFILE_USER_NOT_FOUND.getMessage()
                    ).build()
            ).setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.ERROR_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
            Sentry.getInstance().toLog(
                    DiscordModeratorStates.PLUGIN_NAME.getState(),
                    String.format(
                            DiscordModeratorMessages.SENTRY_PROFILE_NOT_FOUND.getMessage(),
                            Objects.requireNonNull(event.getOption(
                                    DiscordModeratorStates.PLUGIN_OPTION_PROFILE_USER_NAME.getState()
                            )).getAsString()
                    ),
                    LogState.ERROR,
                    event.getMember(),
                    event.getGuild()
            );
        }

        event.replyEmbeds(
                DiscordModeratorProfileCrafter.craftProfileEmbed(
                        member,
                        warnCount
                ).build()
        ).setEphemeral(true).queue();
        Sentry.getInstance().toLog(
                DiscordModeratorStates.PLUGIN_NAME.getState(),
                String.format(
                        DiscordModeratorMessages.SENTRY_PROFILE_SEE.getMessage(),
                        member.getAsMention()
                ),
                LogState.SUCCESSFUL,
                event.getMember(),
                event.getGuild()
        );
    }


    @Override
    public void onModalInteraction(ModalInteractionEvent event) {

        if(event.getModalId().equals(DiscordModeratorStates.PLUGIN_MODAL_WARN_ADD_ID.getState())){
            Member member = Objects.requireNonNull(event.getGuild()).getMemberById(
                    Objects.requireNonNull(event.getValue(
                            DiscordModeratorStates.PLUGIN_TEXT_INPUT_WARN_ADD_USER_ID.getState()
                    )).getAsString()
            );
            String warnName = Objects.requireNonNull(event.getValue(
                    DiscordModeratorStates.PLUGIN_TEXT_INPUT_WARN_ADD_NAME_ID.getState()
            )).getAsString();

            assert member != null;
            DiscordModeratorWarnsDatabase.addWarn(
                    member.getId(),
                    warnName,
                    event.getUser().getId(),
                    DateHourFormatter.getInstance().getDateTimeFormat()
            );
            event.replyEmbeds(
                    DiscordModeratorWarnCrafter.craftSuccessAddWarnEmbed(
                            member,
                            warnName
                    ).build()
            ).setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
            Sentry.getInstance().toLog(
                    DiscordModeratorStates.PLUGIN_NAME.getState(),
                    String.format(
                            DiscordModeratorMessages.SENTRY_WARN_REMOVE.getMessage(),
                            member.getAsMention(),
                            warnName
                    ),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

        if(event.getModalId().equals(DiscordModeratorStates.PLUGIN_MODAL_WARN_REMOVE_ID.getState())){
            String warnId = Objects.requireNonNull(event.getValue(
                    DiscordModeratorStates.PLUGIN_TEXT_INPUT_WARN_REMOVE_WARN_ID.getState()
            )).getAsString();

            Warn warnToRemove = DiscordModeratorWarnsDatabase.getWarnById(warnId);
            if(warnToRemove == null) {
                event.replyEmbeds(
                        ErrorCrafter.craftErrorDescriptionEmbed(
                                DiscordModeratorStates.PLUGIN_NAME.getState(),
                                String.format(
                                        DiscordModeratorMessages.WARN_NOT_FOUND.getMessage(),
                                        warnId
                                )
                        ).build()
                ).setEphemeral(true)
                        .queue(message -> {
                            message.deleteOriginal().queueAfter(
                                    QueueAfterTimes.ERROR_TIME.getQueueAfterTime(),
                                    TimeUnit.SECONDS
                            );
                        });
            }
            Member warnMember = Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getMemberById(
                    warnToRemove.getUser_id()
            ));

            DiscordModeratorWarnsDatabase.removeWarn(warnId);
            event.replyEmbeds(
                DiscordModeratorWarnCrafter.craftSuccessRemoveWarn(
                        warnMember,
                        warnToRemove.getName()
                ).build()
            ).setEphemeral(true)
                    .queue(message -> {
                        message.deleteOriginal().queueAfter(
                                QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                                TimeUnit.SECONDS
                        );
                    });
            Sentry.getInstance().toLog(
                    DiscordModeratorStates.PLUGIN_NAME.getState(),
                    String.format(
                            DiscordModeratorMessages.SENTRY_WARN_REMOVE.getMessage(),
                            warnMember.getAsMention(),
                            warnToRemove.getName()
                    ),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

    }

}
