package fr.skytorstd.doxer.states.plugins;

public enum WeatherStates {
    PLUGIN_NAME("Weather"),

    PLUGIN_COMMAND_WEATHER_PREFIX("weather"),
    PLUGIN_OPTION_WEATHER_VILLE_NAME("ville");

    private final String state;

    WeatherStates(String message) {
        this.state = message;
    }

    public String getState() {
        return state;
    }
}
