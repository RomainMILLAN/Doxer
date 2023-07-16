package fr.skytorstd.doxer.states.plugins;

public enum GrouperStates {
    PLUGIN_NAME("Grouper"),

    PLUGIN_COMMAND_GROUPER_PREFIX("grouper"),
    PLUGIN_OPTION_GROUPER_NAME_NAME("nom");

    private final String state;

    GrouperStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
