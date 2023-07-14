package fr.skytorstd.doxer.states.messages.application;

public enum MemberPermissionMessages {

    OP("Sudo"),
    STAFF("Staff");

    private String message;

    MemberPermissionMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
