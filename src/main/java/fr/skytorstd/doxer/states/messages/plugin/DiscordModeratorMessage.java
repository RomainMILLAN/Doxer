package fr.skytorstd.doxer.states.messages.plugin;

public enum DiscordModeratorMessage {
    PLUGIN_NAME("DiscordModerator"),
    PLUGIN_DESCRIPTION("Plugin de modération pour discord"),

    PLUGIN_COMMAND_1("/warn <utilisateur> <action>"),
    PLUGIN_COMMAND_1_DESCRIPTION("Permet de gérer les warns d'un utilisateur"),
    PLUGIN_COMMAND_2("/mute <utilisateur>"),
    PLUGIN_COMMAND_2_DESCRIPTION("Permet de gérer le mute d'un utilisateur"),
    PLUGIN_COMMAND_3("/profil <utilisateur>"),
    PLUGIN_COMMAND_3_DESCRIPTION("Permet de voir le profil utilisateur"),

    MESSAGE("MESSAGE");

    private final String message;

    DiscordModeratorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
