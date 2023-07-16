package fr.skytorstd.doxer.states.plugins;

public enum PollExclamerStates {
    PLUGIN_NAME("PollExclamer"),

    PLUGIN_COMMAND_POLL_PREFIX("poll"),
    PLUGIN_OPTION_POLL_QUESTION_NAME("question"),
    PLUGIN_OPTION_POLL_ANSWER_NAME("reponses");

    private final String state;

    PollExclamerStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
