package fr.skytorstd.doxer.states.plugins;

public enum DiscordSentryStates {
    PLUGIN_NAME("DiscordSentry"),

    PLUGIN_COMMAND_X_PREFIX("x");

    private final String state;

    DiscordSentryStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
