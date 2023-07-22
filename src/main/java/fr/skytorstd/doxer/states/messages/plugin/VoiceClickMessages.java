package fr.skytorstd.doxer.states.messages.plugin;

public enum VoiceClickMessages {

    PLUGIN_DESCRIPTION("Plugin permettant de créer des channels vocaux"),

    PLUGIN_COMMAND_VOICECLICK("/voiceclick <list/add/remove>"),
    PLUGIN_COMMAND_VOICECLICK_DESCRIPTION("Commande permettant de gérer le plugin VoiceClick"),
    PLUGIN_OPTION_VOICECLICK_ACTION_DESCRIPTION("Action à effectuer"),

    PLUGIN_OPTION_VOICECLICK_CHOICE_LIST_DESCRIPTION("Liste des channels voiceclick"),
    PLUGIN_OPTION_VOICECLICK_CHOICE_ADD_DESCRIPTION("Ajouter un channel voicelick"),
    PLUGIN_OPTION_VOICECLICK_CHOICE_REMOVE_DESCRIPTION("Supprimer un channel voiceclick"),

    PLUGIN_MODAL_VOICECLICK_ADD("Ajout d'un channel voiceclick"),
    PLUGIN_TEXT_INPUT_VOICECLICK_ADD_NEW_CHANNEL_NAME("Nom du nouveau channel"),
    PLUGIN_TEXT_INPUT_VOICECLICK_ADD_NEW_CATEGORY_ID("Identifiant de la catégorie"),
    PLUGIN_TEXT_INPUT_VOICECLICK_ADD_CHANNEL_ID("Identifiant du channel"),

    PLUGIN_MODAL_VOICECLICK_REMOVE("Suppression d'un channel voiceclik"),
    PLUGIN_TEXT_INPUT_VOICECLICK_REMOVE_VC_ID("Identifiant du voiceclick"),

    NO_VOICE_CLICK_CHANNELS("Aucun channel voiceclick"),
    VOICE_CLICK_ADD("Channel Voice Click ajouté %s"),
    VOICE_CLICK_REMOVE("Channel Voice Click supprimé `%s`"),
    VOICE_CLICK_CHANNEL_NOT_FOUND("Channel non trouvé `%s`"),
    VOICE_CLICK_NOT_FOUND("Voice Click non trouvé `%s`"),
    SENTRY_VOICECLICK_LIST("Lecture de la liste des channels voiceclicks"),

    MESSAGE("MESSAGE");

    private final String message;

    VoiceClickMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
