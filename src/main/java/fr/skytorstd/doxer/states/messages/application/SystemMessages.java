package fr.skytorstd.doxer.states.messages.application;

public enum SystemMessages {
    CONFIGURATION_LOADED("Configuration chargée"),
    CONFIGURATION_REGISTER("Configuration enregistrée"),
    PLUGIN_REGISTER_SUCCESS("Plugin enregistré"),
    INCORRECT_COMMAND("Commande incorrecte"),
    INCORRECT_PERMISSION("Permission incorrecte"),
    INCORRECT_PERMISSION_WITH_PERMISSION("Permission incorrecte (`%s`)"),
    INCORRECT_TEXT_CHANNEL("Channel incorrect"),
    INCORRECT_TEXT_CHANNEL_WITH_NAME(INCORRECT_TEXT_CHANNEL.getMessage() + " (`%s`)"),
    INCORRECT_ROLE("Role incorrect"),
    INCORRECT_ROLE_WITH_NAME(INCORRECT_ROLE.getMessage() + " (``)");


    private String message;

    SystemMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
