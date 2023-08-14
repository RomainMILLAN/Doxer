package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.MemberPermission;
import fr.skytorstd.doxer.manager.Sentry;
import fr.skytorstd.doxer.manager.database.plugins.voiceclick.ChannelVoiceClickDatabase;
import fr.skytorstd.doxer.manager.database.plugins.voiceclick.VoiceClickDatabase;
import fr.skytorstd.doxer.manager.embedCrafter.ErrorCrafter;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.VoiceClickCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.objects.plugins.VoiceClick;
import fr.skytorstd.doxer.states.LogState;
import fr.skytorstd.doxer.states.MemberPermissionStates;
import fr.skytorstd.doxer.states.QueueAfterTimes;
import fr.skytorstd.doxer.states.messages.application.SystemMessages;
import fr.skytorstd.doxer.states.messages.plugin.VoiceClickMessages;
import fr.skytorstd.doxer.states.plugins.VoiceClickStates;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

public class voiceClick extends pluginSlashInterface {

    public voiceClick() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        VoiceClickStates.PLUGIN_NAME.getState(),
                        VoiceClickMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {
                            {
                                add(VoiceClickMessages.PLUGIN_COMMAND_VOICECLICK.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equals(VoiceClickStates.PLUGIN_COMMAND_VOICE_CLICK_PREFIX.getState())) {

            if(!MemberPermission.getInstance().isOpMember(event.getMember())){
                event.replyEmbeds(
                        ErrorCrafter.craftErrorPermission(
                                VoiceClickStates.PLUGIN_NAME.getState(),
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
                        VoiceClickStates.PLUGIN_NAME.getState(),
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

            String action = event.getOption(VoiceClickStates.PLUGIN_OPTION_VOICE_CLICK_ACTION_NAME.getState()).getAsString();

            if(action.equals(VoiceClickStates.PLUGIN_OPTION_VOICE_CLICK_CHOICE_LIST_NAME.getState())){
                listVoiceClickCommand(event);
            }

            if(action.equals(VoiceClickStates.PLUGIN_OPTION_VOICE_CLICK_CHOICE_ADD_NAME.getState())){
                addVoiceClickCommand(event);
            }

            if(action.equals(VoiceClickStates.PLUGIN_OPTION_VOICE_CLICK_CHOICE_REMOVE_NAME.getState())){
                removeVoiceClickCommand(event);
            }
        }
    }

    private void listVoiceClickCommand(SlashCommandInteractionEvent event) {
        ArrayList<VoiceClick> voiceClicks = VoiceClickDatabase.voiceClickChannels();

        event.replyEmbeds(
                VoiceClickCrafter.craftVoiceClicksList(voiceClicks).build()
        ).queue();
        Sentry.getInstance().toLog(
                VoiceClickStates.PLUGIN_NAME.getState(),
                VoiceClickMessages.SENTRY_VOICECLICK_LIST.getMessage(),
                event.getCommandString(),
                LogState.SUCCESSFUL,
                event.getMember(),
                event.getGuild()
        );
    }

    private void addVoiceClickCommand(SlashCommandInteractionEvent event) {
        TextInput name = TextInput.create(
                VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_NEW_CHANNEL_NAME.getState(),
                VoiceClickMessages.PLUGIN_TEXT_INPUT_VOICECLICK_ADD_NEW_CHANNEL_NAME.getMessage(),
                TextInputStyle.SHORT
        )
                .setRequired(true)
                .build();

        TextInput channelId = TextInput.create(
                VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_CHANNEL_ID.getState(),
                VoiceClickMessages.PLUGIN_TEXT_INPUT_VOICECLICK_ADD_CHANNEL_ID.getMessage(),
                TextInputStyle.SHORT
        )
                .setRequired(true)
                .build();

        TextInput categoryId = TextInput.create(
                VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_CATEGORY_ID.getState(),
                VoiceClickMessages.PLUGIN_TEXT_INPUT_VOICECLICK_ADD_NEW_CATEGORY_ID.getMessage(),
                TextInputStyle.SHORT
        )
                .setRequired(true)
                .build();


        Modal voiceClickAdd = Modal.create(
            VoiceClickStates.PLUGIN_MODAL_VOICE_CLICK_ADD.getState(),
            VoiceClickMessages.PLUGIN_MODAL_VOICECLICK_ADD.getMessage()
        )
                .addActionRow(name)
                .addActionRow(channelId)
                .addActionRow(categoryId)
                .build();

        event.replyModal(voiceClickAdd).queue();
    }

    private void removeVoiceClickCommand(SlashCommandInteractionEvent event) {
        TextInput vcId = TextInput.create(
                VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_REMOVE_VC_ID.getState(),
                VoiceClickMessages.PLUGIN_TEXT_INPUT_VOICECLICK_REMOVE_VC_ID.getMessage(),
                TextInputStyle.SHORT
        )
                .setRequired(true)
                .build();

        Modal voiceClickRemove = Modal.create(
                VoiceClickStates.PLUGIN_MODAL_VOICE_CLICK_REMOVE.getState(),
                VoiceClickMessages.PLUGIN_MODAL_VOICECLICK_REMOVE.getMessage()
        )
                .addActionRow(vcId)
                .build();

        event.replyModal(voiceClickRemove).queue();
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {

        if(event.getModalId().equals(VoiceClickStates.PLUGIN_MODAL_VOICE_CLICK_ADD.getState())){
            addVoiceClickModal(event);
        }

        if(event.getModalId().equals(VoiceClickStates.PLUGIN_MODAL_VOICE_CLICK_REMOVE.getState())) {
            removeVoiceClickModal(event);
        }

    }

    private void addVoiceClickModal(ModalInteractionEvent event) {
        String name = event.getValue(VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_NEW_CHANNEL_NAME.getState()).getAsString();
        String channelId = event.getValue(VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_CHANNEL_ID.getState()).getAsString();

        Channel channel = event.getGuild().getVoiceChannelById(channelId);
        if(null == channel) {
            event.replyEmbeds(
                    ErrorCrafter.craftErrorDescriptionEmbed(
                            VoiceClickStates.PLUGIN_NAME.getState(),
                            String.format(
                                    VoiceClickMessages.VOICE_CLICK_CHANNEL_NOT_FOUND.getMessage(),
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
                    VoiceClickStates.PLUGIN_NAME.getState(),
                    String.format(
                            VoiceClickMessages.VOICE_CLICK_CHANNEL_NOT_FOUND.getMessage(),
                            channelId
                    ),
                    LogState.ERROR,
                    event.getMember(),
                    event.getGuild()
            );

            return;
        }


        VoiceClick vc = new VoiceClick(
                name,
                channelId
        );
        VoiceClickDatabase.addVoiceClickChannel(vc);
        event.replyEmbeds(
            VoiceClickCrafter.craftSuccessAddVoiceClick(channel).build()
        )
                .setEphemeral(true)
                .queue(message -> {
                    message.deleteOriginal().queueAfter(
                            QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                            TimeUnit.SECONDS
                    );
                });
        Sentry.getInstance().toLog(
                VoiceClickStates.PLUGIN_NAME.getState(),
                String.format(
                        VoiceClickMessages.VOICE_CLICK_ADD.getMessage(),
                        channel.getAsMention()
                ),
                LogState.SUCCESSFUL,
                event.getMember(),
                event.getGuild()
        );
    }

    private void removeVoiceClickModal(ModalInteractionEvent event) {
        String vc_id = event.getValue(VoiceClickStates.PLUGIN_TEXT_INPUT_VOICE_CLICK_REMOVE_VC_ID.getState()).getAsString();
        VoiceClick vc = VoiceClickDatabase.getVoiceClick(vc_id);

        if(null == vc){
            event.replyEmbeds(
                    ErrorCrafter.craftErrorDescriptionEmbed(
                            VoiceClickStates.PLUGIN_NAME.getState(),
                            String.format(
                                    VoiceClickMessages.VOICE_CLICK_NOT_FOUND.getMessage(),
                                    vc_id
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
                    VoiceClickStates.PLUGIN_NAME.getState(),
                    String.format(
                            VoiceClickMessages.VOICE_CLICK_NOT_FOUND.getMessage(),
                            vc_id
                    ),
                    LogState.ERROR,
                    event.getMember(),
                    event.getGuild()
            );
        }

        VoiceClickDatabase.removeVoiceClickChannel(String.valueOf(vc.getVc_id()));
        event.replyEmbeds(
                VoiceClickCrafter.craftSuccessRemoveVoiceClick(vc).build()
        )
                .setEphemeral(true)
                .queue(message -> {
                    message.deleteOriginal().queueAfter(
                            QueueAfterTimes.SUCCESS_TIME.getQueueAfterTime(),
                            TimeUnit.SECONDS
                    );
                });
        Sentry.getInstance().toLog(
                VoiceClickStates.PLUGIN_NAME.getState(),
                String.format(
                        VoiceClickMessages.VOICE_CLICK_REMOVE.getMessage(),
                        vc.getVc_channel_id()
                ),
                LogState.SUCCESSFUL,
                event.getMember(),
                event.getGuild()
        );
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {

        if(event.getChannelJoined() != null){
            VoiceClick vcc = VoiceClickDatabase.getVoiceClickByChannelId(event.getChannelJoined().getId());

            if(vcc != null){
                EnumSet<Permission> allow = EnumSet.of(Permission.MANAGE_CHANNEL, Permission.PRIORITY_SPEAKER,
                        Permission.VOICE_MUTE_OTHERS, Permission.VOICE_DEAF_OTHERS, Permission.VOICE_SPEAK);
                EnumSet<Permission> deny = EnumSet.of(Permission.BAN_MEMBERS, Permission.KICK_MEMBERS);
                event.getChannelJoined().getParentCategory().createVoiceChannel(vcc.getVc_name() + " " + event.getMember().getEffectiveName()).addMemberPermissionOverride(Long.parseLong(event.getMember().getId()), allow, deny).queue(voiceChannel -> {
                    ChannelVoiceClickDatabase.addChannelVoiceClick(voiceChannel.getId());

                    event.getGuild().moveVoiceMember(event.getMember(), event.getGuild().getVoiceChannelById(voiceChannel.getId())).queue();
                });
            }
        }

        if(event.getChannelLeft() != null){
            if(ChannelVoiceClickDatabase.isChannelVoiceClick(event.getChannelLeft().getId())){
                if(event.getChannelLeft().getMembers().isEmpty()){
                    ChannelVoiceClickDatabase.removeChannelVoiceClick(event.getChannelLeft().getId());
                    event.getChannelLeft().delete().queue();
                }
            }
        }

    }
}
