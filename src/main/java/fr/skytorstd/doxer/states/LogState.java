package fr.skytorstd.doxer.states;

import fr.skytorstd.doxer.states.messages.IconMessages;

public enum LogState {

    SUCCESSFUL(
            IconMessages.SUCCESS.getIcon(),
            "(*Réussite*)"
    ),
    ERROR(
            IconMessages.ERROR.getIcon(),
            "(*Erreur*)"
    );

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
