package fr.skytorstd.doxer.states.messages.application;

import fr.skytorstd.doxer.states.messages.IconMessages;

public enum UploadMessages {

    DISCORD_CONNECTED(IconMessages.SUCCESS.getIcon() + " Bot %s connecté"),
    NOTIFICATION_CONNECTED("Bot connecté ✅"),
    DISCORD_DISCONNECTED(IconMessages.SUCCESS.getIcon() + " Bot %s déconnecté"),
    NOTIFICATION_DISCONNECTED("Bot déconnecté ❌");

    private String message;

    UploadMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
