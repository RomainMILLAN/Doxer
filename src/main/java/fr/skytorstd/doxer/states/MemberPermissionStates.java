package fr.skytorstd.doxer.states;

public enum MemberPermissionStates {

    OP("Sudo"),
    STAFF("Staff");

    private String state;

    MemberPermissionStates(String message) {
        this.state = message;
    }

    public String getMessage() {
        return state;
    }

}
