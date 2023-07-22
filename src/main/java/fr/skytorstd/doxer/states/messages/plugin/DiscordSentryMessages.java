package fr.skytorstd.doxer.states.messages.plugin;

public enum DiscordSentryMessages {

    PLUGIN_DESCRIPTION("Plugin permettant de logger toutes les actions effectuée sur un serveur discord"),

    MESSAGES("Message"),
    MESSAGES_UPDATE("Message Update"),
    VOICE_MOVE("Voice Move"),
    VOICE_JOIN("Voice Join"),
    VOICE_QUIT("Voice Quit"),
    VOICE_UPDATE("Voice Update"),
    VOICE_DEFEAN("Voice Defean"),
    VOICE_MUTE("Voice Mute"),
    VOICE_DEFEAN_ON("Sourdine"),
    VOICE_DEFEAN_OFF("Non Sourdine"),
    VOICE_MUTE_ON("Muet"),
    VOICE_MUTE_OFF("Non Muet"),
    MEMBER_NICKNAME_UPDATE("Nickname Update"),
    GUILD_MEMBER_JOIN("Member Join");

    private final String message;

    DiscordSentryMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
