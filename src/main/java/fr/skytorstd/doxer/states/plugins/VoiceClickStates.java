package fr.skytorstd.doxer.states.plugins;

public enum VoiceClickStates {
    PLUGIN_NAME("VoiceClick"),

    PLUGIN_COMMAND_VOICE_CLICK_PREFIX("voiceclick"),
    PLUGIN_OPTION_VOICE_CLICK_ACTION_NAME("action"),

    PLUGIN_OPTION_VOICE_CLICK_CHOICE_LIST_NAME("list"),
    PLUGIN_OPTION_VOICE_CLICK_CHOICE_ADD_NAME("add"),
    PLUGIN_OPTION_VOICE_CLICK_CHOICE_REMOVE_NAME("remove"),

    PLUGIN_MODAL_VOICE_CLICK_ADD("modal_voice_click_add"),
    PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_NEW_CHANNEL_NAME("ti_voice_click_new_channel_name"),
    PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_CATEGORY_ID("ti_voice_click_category_id"),
    PLUGIN_TEXT_INPUT_VOICE_CLICK_ADD_CHANNEL_ID("ti_voice_click_channel_id"),

    PLUGIN_MODAL_VOICE_CLICK_REMOVE("modal_voice_click_remove"),
    PLUGIN_TEXT_INPUT_VOICE_CLICK_REMOVE_VC_ID("ti_voice_click_vc_id");

    private final String state;

    VoiceClickStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
