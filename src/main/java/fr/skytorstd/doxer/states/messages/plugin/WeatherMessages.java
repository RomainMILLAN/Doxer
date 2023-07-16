package fr.skytorstd.doxer.states.messages.plugin;

public enum WeatherMessages {

    PLUGIN_DESCRIPTION("Plugin permettant de visualiser la météo"),

    PLUGIN_COMMAND_WEATHER("/weather <ville>"),
    PLUGIN_COMMAND_WEATHER_COMMAND("Observer la météo d'une ville"),
    PLUGIN_OPTION_WEATHER_VILLE_DESCRIPTION("Ville à observer"),

    MESSAGE("MESSAGE");

    private final String message;

    WeatherMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
