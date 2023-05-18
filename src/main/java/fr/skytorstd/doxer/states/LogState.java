package fr.skytorstd.doxer.states;

public enum LogState {

    SUCCESSFUL(":white_check_mark:", "(*Réussite*)"),
    ERROR(":x:", "(*Erreur*)");

    private final String emojiMessage;
    private final String statusMessage;

    LogState(String emojiMessage, String statusMessage) {
        this.emojiMessage = emojiMessage;
        this.statusMessage = statusMessage;
    }

    public String getEmojiMessage() {
        return emojiMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
