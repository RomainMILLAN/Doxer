package fr.skytorstd.doxer.states;

public enum ConsoleState {
    INFO(ConsoleColors.BLUE.getConsoleColor() + "INFO" + ConsoleColors.RESET.getConsoleColor()),
    LOG(ConsoleColors.YELLOW.getConsoleColor() + "DISCORD SENTRY" + ConsoleColors.RESET.getConsoleColor()),
    ERROR(ConsoleColors.RED.getConsoleColor() + "ERREUR" + ConsoleColors.RESET.getConsoleColor()),
    DEBUG(ConsoleColors.PURPLE.getConsoleColor() + "DEBUG" + ConsoleColors.RESET.getConsoleColor());

    private String consolePrefix;

    ConsoleState(String consolePrefix) {
        this.consolePrefix = consolePrefix;
    }

    public String getConsolePrefix() {
        return consolePrefix;
    }
}
