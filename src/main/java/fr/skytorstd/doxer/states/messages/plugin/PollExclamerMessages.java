package fr.skytorstd.doxer.states.messages.plugin;

public enum PollExclamerMessages {

    PLUGIN_DESCRIPTION("Plugin permettant de crée des sondages rapidement"),

    PLUGIN_COMMAND_POLL("/poll <question> <:emoji1:, :emoji2:, ...>"),
    PLUGIN_COMMAND_POLL_DESCRIPTION("Commande pour crée un sondage"),
    PLUGIN_OPTION_QUESTION_DESCRIPTION("Question du sondage"),
    PLUGIN_OPTION_ANSWER_DESCRIPTION("Réponse sous formes d'émoji"),

    POLL_OF("Sondage de"),
    POLL_CREATED("Sondage crée"),
    SENTRY_POLL_CREATED("Un sondage crée (`%s`)");

    private final String message;

    PollExclamerMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
