package fr.skytorstd.doxer.states.messages.application;

public enum ConsoleMessages {
    CC_INFO_STOP("!stop - Arrêter le bot"),
    CC_INFO_RELOAD("!reload - Relancer le bot"),
    CC_STOP("Arrêt du bot"),
    CC_RELOAD("Relancement du bot"),
    CC_ERROR_COMMAND("Cette commande n'existe pas");

    private String message;

    ConsoleMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
