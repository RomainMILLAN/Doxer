package fr.skytorstd.doxer.states.messages.plugin;

public enum GrouperMessages {

    PLUGIN_DESCRIPTION("Plugin permettant de créer des groupes dans une catégorie définie"),

    PLUGIN_COMMAND_GROUPER("/grouper <nom_du_groupe>"),
    PLUGIN_COMMAND_GROUPER_DESCRIPTION("Permet de créer un groupe nommé"),
    PLUGIN_OPTION_GROUPER_NAME_DESCRIPTION("Nom du groupe"),

    GROUP_CREATED("Groupe créé (`%s`)"),
    MESSAGE("MESSAGE");

    private final String message;

    GrouperMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
