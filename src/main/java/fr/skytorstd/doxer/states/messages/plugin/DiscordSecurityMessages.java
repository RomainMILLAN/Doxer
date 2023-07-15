package fr.skytorstd.doxer.states.messages.plugin;

public enum DiscordSecurityMessages {

    PLUGIN_DESCRIPTION("Plugin permetant de filtrer les personnes qui rentre sur votre serveur discord"),

    PLUGIN_COMMAND_SECURITY("/security <on/off> <default_group>"),
    PLUGIN_OPTION_SECURITY_STATE_DESCRIPTION("Permet d'activer ou non la sécuritée"),
    PLUGIN_OPTION_SECURITY_DEFAULT_GROUP_DESCRIPTION("Désigne le groupe par défault si la sécurité n'est pas activé"),
    PLUGIN_COMMAND_CONFIRM("/confirm <utilisateur> <groupe>"),
    PLUGIN_OPTION_CONFIRM_USER_DESCRIPTION("Utilisateur à authentifier"),
    PLUGIN_OPTION_CONFIRM_GROUP_DESCRIPTION("Groupe à ajouté à l'utilisateur"),

    MESSAGE("MESSAGE");

    private final String message;

    DiscordSecurityMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
