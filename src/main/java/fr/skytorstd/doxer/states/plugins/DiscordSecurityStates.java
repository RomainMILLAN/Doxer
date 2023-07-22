package fr.skytorstd.doxer.states.plugins;

public enum DiscordSecurityStates {
    PLUGIN_NAME("DiscordSecurity"),

    PLUGIN_COMMAND_SECURITY_PREFIX("security"),

    PLUGIN_COMMAND_CONFIRM_PREFIX("confirm"),
    PLUGIN_OPTION_CONFIRM_USER_NAME("utilisateur"),
    PLUGIN_OPTION_CONFIRM_GROUP_NAME("groupe"),
    PLUGIN_OPTION_CONFIRM_NICKNAME_NAME("nickname");

    private final String state;

    DiscordSecurityStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
