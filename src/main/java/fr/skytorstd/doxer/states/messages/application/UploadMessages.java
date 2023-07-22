package fr.skytorstd.doxer.states.messages.application;

import fr.skytorstd.doxer.states.messages.IconMessages;

public enum UploadMessages {

    DISCORD_CONNECTED(IconMessages.SUCCESS.getIcon() + " Bot %s connecté"),
    SIGNAL_CONNECTED("[%s] ✅ Bot connecté"),
    DISCORD_DISCONNECTED(IconMessages.SUCCESS.getIcon() + " Bot %s déconnecté"),
    SIGNAL_DISCONNECTED("[%s] ❌ Bot déconnecté");

    private String message;

    UploadMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
