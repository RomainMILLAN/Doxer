package fr.skytorstd.doxer.states.messages.application;

public enum SystemMessages {
    CONFIGURATION_LOADED("Configuration chargée"),
    CONFIGURATION_REGISTER("Configuration enregistrée"),
    PLUGIN_REGISTER_SUCCESS("Plugin enregistrée"),
    INCORRECT_COMMAND("Commande incorrecte"),
    INCORRECT_PERMISSION("Permission incorrecte"),
    INCORRECT_PERMISSION_WITH_PERMISSION("Permission incorrecte (`%s`)");


    private String message;

    SystemMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
