package fr.skytorstd.doxer.commands.plugins;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.DiscordSentryManager;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginInterface;
import fr.skytorstd.doxer.states.messages.plugin.DiscordSentryMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSentryStates;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.update.GuildMemberUpdateNicknameEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceDeafenEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.events.role.RoleCreateEvent;

import java.util.ArrayList;

public class discordSentry extends pluginInterface {

    public discordSentry() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        DiscordSentryStates.PLUGIN_NAME.getState(),
                        DiscordSentryMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {}
                )
        );
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if(!e.getAuthor().isBot()){
            DiscordSentryManager.getInstance().toLog(
                    DiscordSentryMessages.MESSAGES.getMessage(),
                    e.getChannel().getAsMention() + "\n > " + e.getMessage().getContentRaw(),
                    e.getMember(),
                    e.getGuild()
            );
        }
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent e) {
        if(!e.getAuthor().isBot()) {
            DiscordSentryManager.getInstance().toLog(
                    DiscordSentryMessages.MESSAGES_UPDATE.getMessage(),
                    e.getChannel().getAsMention() + "\n" + e.getMessage().getContentRaw() + " (*edited*)",
                    e.getMember(),
                    e.getGuild()
            );
        }
    }

    @Override
    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent e) {
        if(!e.getMember().getUser().isBot()){
            if(e.getChannelJoined() != null && e.getChannelLeft() != null)
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_MOVE.getMessage(),
                        e.getChannelLeft() + " -> " + e.getChannelJoined(),
                        e.getMember(),
                        e.getGuild()
                );
            else if(e.getChannelJoined() != null && e.getChannelLeft() == null)
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_JOIN.getMessage(),
                        " -> " + e.getChannelJoined(),
                        e.getMember(),
                        e.getGuild()
                );
            else if(e.getChannelJoined() == null && e.getChannelLeft() != null)
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_QUIT.getMessage(),
                        " <- " + e.getChannelLeft(),
                        e.getMember(),
                        e.getGuild()
                );
            else
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_UPDATE.getMessage(),
                        e.getChannelLeft() + " -> " + e.getChannelJoined(),
                        e.getMember(),
                        e.getGuild()
                );
        }
    }

    @Override
    public void onGuildVoiceDeafen(GuildVoiceDeafenEvent e) {
        if(!e.getMember().getUser().isBot()){
            if(e.getVoiceState().isDeafened()){
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_DEFEAN.getMessage(),
                        DiscordSentryMessages.VOICE_DEFEAN_ON.getMessage(),
                        e.getMember(),
                        e.getGuild()
                );
            }else {
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_DEFEAN.getMessage(),
                        DiscordSentryMessages.VOICE_DEFEAN_OFF.getMessage(),
                        e.getMember(),
                        e.getGuild()
                );
            }
        }
    }

    @Override
    public void onGuildVoiceMute(GuildVoiceMuteEvent e) {
        if(!e.getMember().getUser().isBot()){
            if(e.getVoiceState().isMuted()){
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_MUTE.getMessage(),
                        DiscordSentryMessages.VOICE_MUTE_ON.getMessage(),
                        e.getMember(),
                        e.getGuild()
                );
            }else {
                DiscordSentryManager.getInstance().toLog(
                        DiscordSentryMessages.VOICE_MUTE.getMessage(),
                        DiscordSentryMessages.VOICE_MUTE_ON.getMessage(),
                        e.getMember(),
                        e.getGuild()
                );
            }
        }
    }

    @Override
    public void onGuildMemberUpdateNickname(GuildMemberUpdateNicknameEvent e) {
        DiscordSentryManager.getInstance().toLog(
                DiscordSentryMessages.MEMBER_NICKNAME_UPDATE.getMessage(),
                e.getOldNickname() + " -> " + e.getNewNickname(),
                e.getMember(),
                e.getGuild()
        );
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        DiscordSentryManager.getInstance().toLog(
                DiscordSentryMessages.GUILD_MEMBER_JOIN.getMessage(),
                e.getMember().getEffectiveName() + " -> " + e.getGuild().getName(),
                e.getMember(),
                e.getGuild()
        );
    }
}
