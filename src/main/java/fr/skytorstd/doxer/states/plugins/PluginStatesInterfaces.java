package fr.skytorstd.doxer.states.plugins;

public enum PluginStatesInterfaces {
    PLUGIN_NAME("PluginName"),

    PLUGIN_COMMAND_X_PREFIX("x");

    private final String state;

    PluginStatesInterfaces(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
