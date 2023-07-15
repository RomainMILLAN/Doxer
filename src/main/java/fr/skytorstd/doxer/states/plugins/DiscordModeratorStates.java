package fr.skytorstd.doxer.states.plugins;

public enum DiscordModeratorStates {
    PLUGIN_NAME("DiscordModerator"),

    PLUGIN_COMMAND_WARN_PREFIX("warn"),
    PLUGIN_OPTION_WARN_USER_NAME("utilisateur"),
    PLUGIN_OPTION_WARN_ACTION_NAME("warn"),
    PLUGIN_CHOICE_WARN_SHOW_NAME("show"),
    PLUGIN_CHOICE_WARN_ADD_NAME("add"),
    PLUGIN_CHOICE_WARN_REMOVE_NAME("remove"),
    PLUGIN_TEXT_INPUT_WARN_ADD_NAME_ID("text_input_warn_add_name"),
    PLUGIN_TEXT_INPUT_WARN_ADD_USER_ID("text_input_warn_add_userid"),
    PLUGIN_TEXT_INPUT_WARN_ADD_STAFF_ID("text_input_warn_add_staffid"),
    PLUGIN_MODAL_WARN_ADD_ID("modal_warn_add"),
    PLUGIN_TEXT_INPUT_WARN_REMOVE_WARN_ID("text_input_warn_remove_warn_id"),
    PLUGIN_MODAL_WARN_REMOVE_ID("modal_warn_remove"),

    PLUGIN_COMMAND_PROFILE_PREFIX("profile"),
    PLUGIN_OPTION_PROFILE_USER_NAME("utilisateur");


    private final String state;

    DiscordModeratorStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
