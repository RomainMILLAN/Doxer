package fr.skytorstd.doxer.states.messages.plugin;

public enum MessageMoverMessages {

    PLUGIN_DESCRIPTION("Plugin permettant de déplacer des messages dans d'autres channels"),

    PLUGIN_COMMAND_MOVE("/move <message_id> <channel_id>"),
    PLUGIN_COMMAND_MOVE_DESCRIPTION("Commande permettant de déplacer un message vers un autre channel"),
    PLUGIN_OPTION_MOVE_MESSAGE_ID_DESCRIPTION("Identifiant du message à déplacer"),
    PLUGIN_OPTION_MOVE_CHANNEL_ID_DESCRIPTION("Channel ou déplacer le message"),

    ERROR_CHANNEL_NOT_FOUND("Channel non trouvée (`%s`)"),
    MESSAGE_MOVE("Message déplacé vers %s"),

    SENTRY_CHANNEL_NOT_FOUND("Channel non trouvée (`%s`)"),
    SENTRY_MESSAGE_MOVE("Message déplacé vers (`%s` -> %s)"),
    MESSAGE("MESSAGE");

    private final String message;

    MessageMoverMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
