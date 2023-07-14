package fr.skytorstd.doxer.states.messages.plugin;

public enum DiscordModeratorMessage {
    PLUGIN_NAME("DiscordModerator"),
    PLUGIN_DESCRIPTION("Plugin de modération pour discord"),

    PLUGIN_COMMAND_WARN_PREFIX("warn"),
    PLUGIN_COMMAND_WARN_NAME("/warn <action> <utilisateur>"),
    PLUGIN_COMMAND_WARN_DESCRIPTION("Voir la liste des warns d'un utilisateur"),
    PLUGIN_OPTION_WARN_USER_NAME("utilisateur"),
    PLUGIN_OPTION_WARN_USER_DESCRIPTION("L'utilisateur dont vous voulez voir ces warns"),
    PLUGIN_OPTION_WARN_ACTION_NAME("warn"),
    PLUGIN_OPTION_WARN_ACTION_DESCRIPTION("Action à effectuer"),
    PLUGIN_CHOICE_WARN_SHOW_NAME("show"),
    PLUGIN_CHOICE_WARN_SHOW_DESCRIPTION("Lister les warns d'un utilisateur"),
    PLUGIN_CHOICE_WARN_ADD_NAME("add"),
    PLUGIN_CHOICE_WARN_ADD_DESCRIPTION("Ajouter un warn à l'utilisateur"),
    PLUGIN_CHOICE_WARN_REMOVE_NAME("remove"),
    PLUGIN_CHOICE_WARN_REMOVE_DESCRIPTION("Supprimer un warn à un utilisateur"),


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
