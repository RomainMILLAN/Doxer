package fr.skytorstd.doxer.states.messages.application;

public enum BotMessages {
    ACTIVITY_PLAYING_BOT("Doxer V2 - In progress"),
    JDA_BOT_INITIALIZING("Initialization de Doxer"),
    JDA_BOT_CONNECTED("Doxer est connecté"),
    JDA_BOT_READY("Doxer est prêt");

    private String message;

    BotMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
