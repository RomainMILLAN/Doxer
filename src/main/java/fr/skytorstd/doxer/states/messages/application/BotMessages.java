package fr.skytorstd.doxer.states.messages.application;

public enum BotMessages {
    BOT_NAME("Doxer"),
    ACTIVITY_PLAYING_BOT(BOT_NAME.getMessage() + " - In progress"),
    JDA_BOT_INITIALIZING("Initialization de " + BOT_NAME.getMessage()),
    JDA_BOT_CONNECTED(BOT_NAME.getMessage() +" est connecté"),
    JDA_BOT_READY(BOT_NAME.getMessage() + " est prêt");

    private String message;

    BotMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
