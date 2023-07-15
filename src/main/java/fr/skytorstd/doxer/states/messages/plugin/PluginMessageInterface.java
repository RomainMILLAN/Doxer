package fr.skytorstd.doxer.states.messages.plugin;

public enum PluginMessageInterface {

    PLUGIN_DESCRIPTION("PLUGIN_DESCRIPTION"),

    PLUGIN_COMMAND_1("/COMMAND <1>"),

    MESSAGE("MESSAGE");

    private final String message;

    PluginMessageInterface(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
