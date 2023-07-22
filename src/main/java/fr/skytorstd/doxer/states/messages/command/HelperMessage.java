package fr.skytorstd.doxer.states.messages.command;

public enum HelperMessage {
    COMMAND_NAME("Helper"),

    COMMAND_DESCRIPTION("Commande qui permet de vous aider avec le commandes de Doxer"),

    COMMAND_HELPER_PREFIX("help"),

    COMMAND_PLUGINS_PREFIX("plugins"),

    COMMAND_1("/" + COMMAND_HELPER_PREFIX.getMessage() + " <plugin>"),

    COMMAND_1_OPTION_1_PLUGIN_NAME("plugin"),

    COMMAND_1_OPTION_1_PLUGIN_DESCRIPTION("Plugin pour lequel vous voulez de l'aide"),

    COMMAND_2("/" + COMMAND_PLUGINS_PREFIX.getMessage()),

    NO_PLUGIN_ACTIVATED("Aucun plugin activé"),

    NO_PLUGIN_FOUND("Le plugin demandé n'existe pas"),

    SENTRY_PLUGINS_COMMAND_ACTIVATE("Visualisation de la liste des plugins"),

    SENTRY_PLUGIN_COMMAND_ACTIVATE("Visualisation des commandes de "),

    SENTRY_PLUGIN_COMMAND_ACTIVATE_PLUGIN_NOT_FOUND("Impossible de visualiser les commandes demandées ");

    private final String message;

    HelperMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
