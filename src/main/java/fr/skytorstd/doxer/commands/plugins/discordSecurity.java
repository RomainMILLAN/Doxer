package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.ConsoleManager;
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
import fr.skytorstd.doxer.states.plugins.DiscordModeratorStates;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
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

        if(event.getName().equalsIgnoreCase(DiscordSecurityStates.PLUGIN_COMMAND_SECURITY_PREFIX.getState())
        || event.getName().equalsIgnoreCase(DiscordSecurityStates.PLUGIN_COMMAND_CONFIRM_PREFIX.getState())){
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
        }

        if(event.getName().equalsIgnoreCase(DiscordSecurityStates.PLUGIN_COMMAND_SECURITY_PREFIX.getState())) {
            securityCommand(event);
        }

        if(event.getName().equalsIgnoreCase(DiscordSecurityStates.PLUGIN_COMMAND_CONFIRM_PREFIX.getState())) {
            confirmCommand(event);
        }

    }

    private void securityCommand(SlashCommandInteractionEvent event) {
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
        Member member = event.getOption(
                DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_USER_NAME.getState()
        ).getAsMember();
        Role defaultGroup = event.getGuild().getRoleById(
                App.getConfiguration("R_SECURITY_DEFAULT_GROUP")
        );

        if(null == member) {
            event.replyEmbeds(
                    ErrorCrafter.craftErrorDescriptionEmbed(
                            DiscordModeratorStates.PLUGIN_NAME.getState(),
                            String.format(
                                    SystemMessages.INCORRECT_PERMISSION_WITH_PERMISSION.getMessage(),
                                    MemberPermissionStates.STAFF.getMessage()
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
                    DiscordSecurityStates.PLUGIN_NAME.getState(),
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
        event.getGuild().addRoleToMember(member, defaultGroup).queue();

        if(null != event.getOption(DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_GROUP_NAME.getState())){
            Role role = event.getOption(
                    DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_GROUP_NAME.getState()
            ).getAsRole();

            event.getGuild().addRoleToMember(member, role).queue();
        }

        if(null != event.getOption(DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_NICKNAME_NAME.getState())){
            String nickname = event.getOption(
                    DiscordSecurityStates.PLUGIN_OPTION_CONFIRM_NICKNAME_NAME.getState()
            ).getAsString();

            member.modifyNickname(nickname).queue();
        }

        event.replyEmbeds(
            DiscordSecurityCrafter.craftConfirmEmbed(member, event.getMember()).build()
        ).queue();
        Sentry.getInstance().toLog(
                DiscordSecurityStates.PLUGIN_NAME.getState(),
                String.format(
                        DiscordSecurityMessages.SENTRY_CONFIRM.getMessage(),
                        member.getAsMention()
                ),
                event.getCommandString(),
                LogState.SUCCESSFUL,
                event.getMember(),
                event.getGuild()
        );
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {

        if(App.getConfiguration("ST_SECURITY").equals("TRUE")) {
            TextChannel securityChannel = event.getGuild().getTextChannelById(App.getConfiguration("TC_SECURITY"));

            Role staff = event.getGuild().getRoleById(App.getConfiguration("R_STAFF"));
            securityChannel.sendMessage(
                    String.format(
                            DiscordSecurityMessages.WELCOME_SECURITY_MESSAGE.getMessage(),
                            event.getMember().getAsMention(),
                            staff.getAsMention()
                    )
            ).queue();


        }else {
            Role defaultRole = event.getGuild().getRoleById(App.getConfiguration("R_SECURITY_DEFAULT_GROUP"));
            event.getGuild().addRoleToMember(event.getMember(), defaultRole).queue();

            Sentry.getInstance().toLog(
                    DiscordSecurityStates.PLUGIN_NAME.getState(),
                    String.format(
                            DiscordSecurityMessages.SENTRY_SECURITY_FALSE_MEMBER_JOIN.getMessage(),
                            defaultRole.getAsMention()
                    ),
                    LogState.SUCCESSFUL,
                    event.getMember(),
                    event.getGuild()
            );
        }

    }
}
