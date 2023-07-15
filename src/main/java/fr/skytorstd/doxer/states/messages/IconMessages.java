package fr.skytorstd.doxer.states.messages;

public enum IconMessages {

    SUCCESS(":white_check_mark:"),

    ERROR(":x:"),

    DOCUMENT("\uD83D\uDCC4"),

    WRENCH("\uD83D\uDD27"),

    DOCUMENT_WRITING("\uD83D\uDCDD"),

    CADENA_LOCK("\uD83D\uDD12"),

    PROFILE_ICON("✍\uFE0F");




    private String icon;

    IconMessages(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

}
