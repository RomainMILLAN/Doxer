package fr.skytorstd.doxer.states.plugins;

public enum MessageMoverStates {
    PLUGIN_NAME("MessageMover"),

    PLUGIN_COMMAND_MOVE_PREFIX("move"),
    PLUGIN_OPTION_MOVE_MESSAGE_ID_NAME("messageid"),
    PLUGIN_OPTION_MOVE_CHANNEL_ID_NAME("channelid");

    private final String state;

    MessageMoverStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
